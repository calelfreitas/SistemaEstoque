package Controles;

import Entidades.Usuario;
import Entidades.Admin;
import Entidades.Comum;
import Entidades.SegurancaSenha;
import java.util.ArrayList; 
import java.util.List;

/**
 *
 * @author Calel Freitas e ChatGPT
 */
//Classe responsável por gerenciar a lista de usuários do sistema. 
public class UsuarioControle {
    public static int ultimoIdGlobal = 1; // Acesso global para criação em telas
    private static List<Usuario> usuarios = new ArrayList<>();

    static {
        usuarios.add(new Admin(ultimoIdGlobal++, "Administrador", "admin@uepa.br", "admin123", true));
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        if (buscarPorLogin(usuario.getLogin()) != null) return false;

        String salt = SegurancaSenha.gerarSalt();
        String hash = SegurancaSenha.hashSenha(usuario.getSenha(), salt);

        usuario.setSalt(salt);
        usuario.setSenhaHash(hash);
        usuario.setSenha(null);
        usuarios.add(usuario);

        return true;
    }

    public Usuario buscarPorLogin(String login) {
        for (Usuario u : usuarios) {
            if (u.getLogin().equalsIgnoreCase(login)) return u;
        }
        return null;
    }

    public boolean editarUsuario(String login, String novoNome, String novaSenha, String novoTipo) {
        Usuario u = buscarPorLogin(login);
        if (u != null) {
            u.setNome(novoNome);
            u.setSenha(novaSenha);

            Usuario novoUsuario;
            if (novoTipo.equalsIgnoreCase("Administrador")) {
                novoUsuario = new Admin(u.getId(), novoNome, login, novaSenha, u.isAtivo());
            } else {
                novoUsuario = new Comum(u.getId(), novoNome, login, novaSenha, u.isAtivo());
            }
            usuarios.remove(u);
            cadastrarUsuario(novoUsuario);
            return true;
        }
        return false;
    }

    public boolean inativarUsuario(String login) {
        Usuario u = buscarPorLogin(login);
        if (u != null) {
            u.setAtivo(false);
            return true;
        }
        return false;
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }
}