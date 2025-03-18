package bookdouro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {
    public static void inserirUsuario(String CPF_usuario, String Nome_usuario, String Email_usuario, String Telefone_usuario, String Endereco_usuario, String Senha_usuario, String Funcao) {
        String sql = "INSERT INTO mydb.usuario (CPF_usuario, Nome_usuario, Email_usuario, Telefone_usuario, Endereco_usuario, Senha_usuario, Funcao) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConnectionDataBase.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, CPF_usuario);
            stmt.setString(2, Nome_usuario);
            stmt.setString(3, Email_usuario);
            stmt.setString(4, Telefone_usuario);
            stmt.setString(5, Endereco_usuario);
            stmt.setString(6, Senha_usuario);
            stmt.setString(7, Funcao);
            stmt.executeUpdate();
            System.out.println("Usuário inserido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}