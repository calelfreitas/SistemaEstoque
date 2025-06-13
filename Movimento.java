package Entidades;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
/**
 *
 * @author Calel Freitas
 */
public class Movimento {
    private LocalDateTime data;
    private String tipo;
    private Produto produto;
    private int quantidade;
    
public Movimento(Date data, Produto produto, String tipo, int quantidade){
    this.data = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    this.produto = produto;
    this.tipo = tipo;
    this.quantidade = quantidade;
}
public LocalDateTime getData(){
    return data;
}
public void setData(LocalDateTime dataHora){
    this.data = dataHora;
}
public String getTipo(){
    return tipo;
}
public void setTipo(String tipo){
    this.tipo = tipo;
}
public Produto getProduto(){
    return produto;
}
public void setProduto(Produto produto){
    this.produto = produto;
}
public int getQuantidade(){
    return quantidade;
}
public void setQuantidade(int quantidade){
    this.quantidade = quantidade;
}
}
