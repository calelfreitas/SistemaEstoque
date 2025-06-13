package Controles;

/**
 *
 * @author Calel Freitas e ChatGPT
 */
import Entidades.Usuario;
import Entidades.SegurancaSenha;

public class LoginControle {
    private UsuarioControle usuarioControle = new UsuarioControle();

    public Usuario autenticarUsuario(String login, String senhaDigitada) {
        Usuario usuario = usuarioControle.buscarPorLogin(login);
        if (usuario == null) 
            return null;

        boolean senhaCorreta = SegurancaSenha.verificarSenha(
            senhaDigitada,
            usuario.getSenhaHash(),
            usuario.getSalt()
        );

        return senhaCorreta ? usuario : null;
    }
}
