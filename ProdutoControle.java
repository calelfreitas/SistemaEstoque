package Controles;

import Entidades.Produto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Calel Freitas
 */
public class ProdutoControle {
    private static List<Produto> listaProdutos = new ArrayList<>();
    private static Set<Integer> codigosGerados = new HashSet<>();
    private static Random random = new Random();
    private static int ultimoId = 0;
    //gera codigo aleatorio unico
    private int gerarCodigoAleatorio(){
        int codigo;
          do{
              codigo=1000+random.nextInt(9000);//gera codigo entre 1000 e 9000
          }while(codigosGerados.contains(codigo));
          codigosGerados.add(codigo);
          return codigo;
          }
    //gera id sequencial (1,2,3,4...)
    private int gerarNovoId(){
        return ++ultimoId;
    }
    //cadastra produto e adiciona um produto na lista simulando um BD
    public void cadastrarProduto(Produto produto){
        produto.setId(gerarNovoId());// ID único sequencial
        produto.setCodigo(gerarCodigoAleatorio());// Código aleatório entre 1000–9999
        produto.setQtdAtual(produto.getQtdInicial());// Quantidade atual começa igual à inicial
        listaProdutos.add(produto);
    }
    //retorna todos os produtos cadastrados
    public List<Produto> listarProdutos(){
        return listaProdutos;
    } 
    //busca por codigo
    public Produto buscarPorCodigo(int codigo) {
    for (Produto p : listaProdutos) {
        if (p.getCodigo() == codigo) {
            return p;
        }
    }
    return null; // se não encontrar
 }
    //busca por nome ou codigo
    public List<Produto> buscarPorNomeOuCodigo(String nome, String codigoTexto) {
    List<Produto> resultado = new ArrayList<>();

    for (Produto p : listaProdutos) {
        boolean nomeOk = nome == null || nome.isEmpty() || p.getNome().equalsIgnoreCase(nome);
        boolean codigoOk = true;

        if (codigoTexto != null && !codigoTexto.isEmpty()) {
            try {
                int codigo = Integer.parseInt(codigoTexto);
                codigoOk = p.getCodigo() == codigo;
            } catch (NumberFormatException e) {
                return null; // sinaliza erro
            }
        }

        if (nomeOk && codigoOk) {
            resultado.add(p);
        }
    }
    return resultado;
  }
    public void removerProduto(Produto produto) {
    listaProdutos.remove(produto);
}
}
