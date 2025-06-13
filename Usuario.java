package Entidades;

/**
 *
 * @author Calel Freitas
 */
public class Usuario{
    protected int id;
    protected String nome;  
    protected String login;
    protected String senha;
    protected boolean ativo;
    private String senhaHash;
    private String salt;
    
    public Usuario(int id, String nome, String login, String senha, boolean ativo){
        this.id=id;
        this.nome=nome;
        this.login=login;
        this.senha=senha;
        this.ativo=ativo;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome=nome;
    }
    public String getSenha(){
        return senha;
    }
    public void setSenha(String senha){
        this.senha=senha;
    }
    public String getLogin(){
        return login;
    }
    public void setLogin(String login){
        this.login = login;
    }
    public boolean isAtivo(){
        return ativo;
    }
    public void setAtivo(boolean ativo){
        this.ativo = ativo;
    }
    public String getSenhaHash(){
        return senhaHash;
    }
    public void setSenhaHash(String senhaHash){
        this.senhaHash = senhaHash;
    }
    public String getSalt(){
        return salt;
    }
    public void setSalt(String salt){
        this.salt = salt;
    }
}
