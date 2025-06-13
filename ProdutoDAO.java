package Dados;

import Entidades.Produto;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Calel Freitas e ChatGPT
 */
public class ProdutoDAO {

    public boolean inserir(Produto p) {
        String sql = "INSERT INTO produto (ID, Nome, Procedencia, QtdInicial, Qtd Atual, Código) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, p.getId());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getProcedencia());
            stmt.setInt(4,p.getQtdInicial());
            stmt.setInt(5,p.getQtdAtual());
            stmt.setInt(6,p.getCodigo());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
            return false;
        }
    }

    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto(
                    rs.getInt("ID"),
                    rs.getString("Nome"),
                    rs.getString("Procedência"),
                    rs.getInt("Qtd inicial"),
                    rs.getInt("Qtd atual"),
                    rs.getInt("Cógigo")         
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }

        return lista;
    }
}