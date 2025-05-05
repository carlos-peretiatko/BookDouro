package code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class LivroDAO {

    // CRUD

    // Create
    public static void inserirLivro(String ISBN, String Titulo, String Autor, String Genero, String Editora,
            int Numero_de_exemplares, int Ano_de_publicacao) {
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

    // read
    public static void listarLivros() {
        String sql = "SELECT * FROM mydb.Livro";

        try (Connection conexao = ConnectionDataBase.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                var rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("------------------------");
                System.out.println("ISBN: " + rs.getString("ISBN"));
                System.out.println("Titulo: " + rs.getString("Titulo"));
                System.out.println("Autor(s): " + rs.getString("Autor"));
                System.out.println("Gênero: " + rs.getString("Genero"));
                System.out.println("Editora: " + rs.getString("Editora"));
                System.out.println("Número de Exemplares: " + rs.getInt("Numero_de_exemplares"));
                System.out.println("Ano de Publicação: " + rs.getInt("Ano_de_publicacao"));
                System.out.println("------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // update
    public static void editarLivro(Scanner scanner) {
        System.out.println("Informe o ISBN do livro que deseja editar: ");
        String isbn = scanner.nextLine();

        System.out.println("Informe o novo Título: ");
        String titulo = scanner.nextLine();
        System.out.println("Informe o novo Autor: ");
        String autor = scanner.nextLine();
        System.out.println("Informe o novo Gênero: ");
        String genero = scanner.nextLine();
        System.out.println("Informe a nova Editora: ");
        String editora = scanner.nextLine();
        System.out.println("Informe o novo Número de Exemplares: ");
        int numeroExemplares = scanner.nextInt();
        System.out.println("Informe o novo Ano de Publicação: ");
        int anoPublicacao = scanner.nextInt();

        String sql = "UPDATE mydb.Livro SET Titulo = ?, Autor = ?, Genero = ?, Editora = ?, Numero_de_exemplares = ?, Ano_de_publicacao = ? WHERE ISBN = ?";

        try (Connection conexao = ConnectionDataBase.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            stmt.setString(2, autor);
            stmt.setString(3, genero);
            stmt.setString(4, editora);
            stmt.setInt(5, numeroExemplares);
            stmt.setInt(6, anoPublicacao);
            stmt.setString(7, isbn);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livro atualizado com sucesso!");
            } else {
                System.out.println("Livro não encontrado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // delete
    public static void excluirLivro(Scanner scanner) {
        System.out.println("Informe o ISBN do livro que deseja excluir: ");
        String isbn = scanner.nextLine();

        String sql = "DELETE FROM mydb.Livro WHERE ISBN = ?";

        try (Connection conexao = ConnectionDataBase.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, isbn);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livro excluído com sucesso!");
            } else {
                System.out.println("Livro não encontrado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}