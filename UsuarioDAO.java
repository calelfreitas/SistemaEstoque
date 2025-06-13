package Dados;

import Entidades.Usuario;
import Entidades.Admin;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Calel Freitas e ChatGPT
 */
public class UsuarioDAO {

    public boolean inserir(Usuario u) {
        String sql = "INSERT INTO usuario (nome, login, senha, tipo, ativo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getLogin());
            stmt.setString(3, u.getSenha());
            stmt.setString(4, u instanceof Admin ? "Admin" : "Comum");
            stmt.setBoolean(5, u.isAtivo());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
            return false;
        }
    }

    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getBoolean("ativo")
                );
                lista.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
        return lista;
    }

    public boolean inativar(String login) {
        String sql = "UPDATE usuario SET ativo = false WHERE login = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}