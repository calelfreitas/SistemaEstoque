package Entidades;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

/**
 *
 * @author Calel Freitas e ChatGPT
 */
public class SegurancaSenha {

    public static String gerarSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashSenha(String senha, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(Base64.getDecoder().decode(salt));
            byte[] hashed = md.digest(senha.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hashed);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar hash da senha", e);
        }
    }

    public static boolean verificarSenha(String senhaDigitada, String hashSalvo, String saltSalvo) {
        String hashTentativa = hashSenha(senhaDigitada, saltSalvo);
        return hashTentativa.equals(hashSalvo);
    }
}