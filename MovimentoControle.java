package Controles;

import Entidades.Produto;
import Entidades.Movimento;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Calel Freitas
 */
public class MovimentoControle {
    private static List<Movimento> historico = new ArrayList<>();
    private ProdutoControle produtoControle = new ProdutoControle();

    public boolean registrarMovimento(int codigo, int quantidade, String tipo) {
        Produto produto = produtoControle.buscarPorCodigo(codigo);
        if (produto == null) {
            return false;
        }

        if (tipo.equalsIgnoreCase("Entrada")) {
            produto.setQtdAtual(produto.getQtdAtual() + quantidade);
        } else if (tipo.equalsIgnoreCase("Saída")) {
            if (produto.getQtdAtual() < quantidade) return false;
            produto.setQtdAtual(produto.getQtdAtual() - quantidade);
        } else {
            return false;
        }

        Movimento movimento = new Movimento(new Date(), produto, tipo, quantidade);
        historico.add(movimento);
        return true;
    }

    public List<Movimento> getHistorico() {
        return historico;
    }

    public List<Movimento> filtrarPorProduto(int codigo) {
        List<Movimento> resultado = new ArrayList<>();
        for (Movimento m : historico) {
            if (m.getProduto().getCodigo() == codigo) {
                resultado.add(m);
            }
        }
        return resultado;
    }
    public List<Movimento> filtrarPorDataOuProduto(Date inicio, Date fim, int codigoProduto) {
    List<Movimento> filtrados = new ArrayList<>();

    // Converte para LocalDateTime se datas forem fornecidas
    LocalDateTime inicioLDT = null;
    LocalDateTime fimLDT = null;

    if (inicio != null && fim != null) {
        inicioLDT = inicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        fimLDT = fim.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    for (Movimento mov : historico) {
        boolean dentroIntervalo = true;
        boolean mesmoProduto = true;

        // Se datas forem fornecidas, aplica o filtro de datas
        if (inicioLDT != null && fimLDT != null) {
            dentroIntervalo = !mov.getData().isBefore(inicioLDT) && !mov.getData().isAfter(fimLDT);
        }

        // Se código for diferente de -1, aplica filtro de produto
        if (codigoProduto != -1) {
            mesmoProduto = mov.getProduto().getCodigo() == codigoProduto;
        }

        // Adiciona apenas se todos os filtros aplicáveis forem atendidos
        if (dentroIntervalo && mesmoProduto) {
            filtrados.add(mov);
        }
    }

    return filtrados;
  }
}