package code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LivroDAO {
    public static void inserirUsuario(String ISBN, String Titulo, String Autor, String Genero, String Editora, int Numero_de_exemplares, int Ano_de_publicacao) {
        String sql = "INSERT INTO mydb.livro (ISBN, Titulo, Autor, Genero, Editora, Numero_de_exemplares, Ano_de_publicacao) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConnectionDataBase.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, ISBN);
            stmt.setString(2, Titulo);
            stmt.setString(3, Autor);
            stmt.setString(4, Genero);
            stmt.setString(5, Editora);
            stmt.setInt(6, Numero_de_exemplares);
            stmt.setInt(7, Ano_de_publicacao);
            stmt.executeUpdate();
            System.out.println("Livro inserido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}