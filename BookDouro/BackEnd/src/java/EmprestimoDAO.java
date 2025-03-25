package java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmprestimoDAO {
    public static void inserirEmprestimo(int Usuario_Id_usuario, String Livro_ISBN, String Data_de_inicio, String Data_de_devolucao) {
        String sql = "INSERT INTO mydb.emprestimo (Usuario_Id_usuario, Livro_ISBN, Data_de_inicio, Data_de_devolucao) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConnectionDataBase.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, Usuario_Id_usuario);
            stmt.setString(2, Livro_ISBN);
            stmt.setString(3, Data_de_inicio);
            stmt.setString(4, Data_de_devolucao);
            stmt.executeUpdate();
            System.out.println("Empréstimo registrado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}