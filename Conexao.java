package Dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Calel Freitas e ChatGPT
 */
public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_estoque";
    private static final String USUARIO = "root";  // altere conforme necessário
    private static final String SENHA = "";        // altere conforme necessário

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
