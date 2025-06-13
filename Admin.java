package Entidades;

/**
 *
 * @author Calel Freitas 
 */
// Representa um usuário do tipo administrador
public class Admin extends Usuario{
    public Admin(int id, String nome, String login, String senha,boolean ativo){
        super(id,nome,login,senha,ativo);
    }
    // Métodos específicos de admin podem ser adicionados aqui futuramente
}
