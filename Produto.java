package Entidades;

/**
 *
 * @author Calel Freitas
 */
public class Produto {
    private int id;
    private String nome;
    private String procedencia;
    private int qtdInicial;
    private int qtdAtual;
    private int codigo;
    
public Produto(int id, String nome, String procedencia, int qtdInicial, int qtdAtual, int codigo){
    this.id=id;
    this.nome=nome;
    this.procedencia=procedencia;
    this.qtdInicial=qtdInicial;
    this.qtdAtual=qtdAtual;
    this.codigo=codigo;
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
public String getProcedencia(){
    return procedencia;
}
public void setProcedencia(String procedencia){
    this.procedencia=procedencia;
}
public int getQtdInicial(){
    return qtdInicial;
}
public void setQtdInicial(int qtdInicial){
    this.qtdInicial=qtdInicial;
}
public int getQtdAtual(){
    return qtdAtual;
}
public void setQtdAtual(int qtdAtual){
    this.qtdAtual=qtdAtual;
}
public int getCodigo(){
    return codigo;
}
public void setCodigo(int codigo){
    this.codigo=codigo;
}
}
