package Dados;

import Entidades.Movimento;
import Entidades.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Calel Freitas e ChatGPT
 */
public class MovimentoDAO {

    private final Conexao conexao;

    public MovimentoDAO() {
        this.conexao = new Conexao();
    }

    public void registrarMovimento(Movimento m) {
        String sql = "INSERT INTO movimento (data_movimento, tipo, quantidade, id_produto) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(m.getData()));
            stmt.setString(2, m.getTipo());
            stmt.setInt(3, m.getQuantidade());
            stmt.setInt(4, m.getProduto().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Movimento> listarMovimentos() {
        List<Movimento> lista = new ArrayList<>();
        String sql = "SELECT m.*, p.id as prod_id, p.nome as prod_nome, p.tipo as prod_tipo, p.estoque_min, p.estoque_max, p.qtd_estoque " +
                     "FROM movimento m JOIN produto p ON m.id_produto = p.id";
        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto(
                    rs.getInt("prod_id"),
                    rs.getString("prod_nome"),
                    rs.getString("prod_tipo"),
                    rs.getInt("qtd_estoque"),
                    rs.getInt("estoque_min"),
                    rs.getInt("estoque_max")
                );

                Movimento m = new Movimento(
                    rs.getTimestamp("data_movimento"),
                    p,
                    rs.getString("tipo"),
                    rs.getInt("quantidade")
                );

                lista.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
