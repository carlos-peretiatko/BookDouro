package code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class EmprestimoDAO {

    //CRUD

    //create
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

    //read
    public static void listarEmprestimos() {
        String sql = "SELECT * FROM mydb.Emprestimo";

        try (Connection conexao = ConnectionDataBase.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                var rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("------------------------");
                System.out.println("Código do Empréstimo: " + rs.getInt("Codigo_emprestimo"));
                System.out.println("ID do Usuário: " + rs.getInt("Usuario_Id_usuario"));
                System.out.println("ISBN do Livro: " + rs.getString("Livro_ISBN"));
                System.out.println("Data de Início: " + rs.getString("Data_de_inicio"));
                System.out.println("Data de Devolução: " + rs.getString("Data_de_devolucao"));
                System.out.println("------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //update
    public static void editarEmprestimo(Scanner scanner) {
        System.out.println("Informe o Código do Empréstimo que deseja editar: ");
        int codigoEmprestimo = scanner.nextInt();

        System.out.println("Informe o novo ID do Usuário: ");
        int usuarioId = scanner.nextInt();

        System.out.println("Informe o novo ISBN do Livro: ");
        String livroISBN = scanner.next();

        System.out.println("Informe a nova Data de Início (AAAA-MM-DD): ");
        String dataInicio = scanner.next();

        System.out.println("Informe a nova Data de Devolução (AAAA-MM-DD): ");
        String dataDevolucao = scanner.next();

        String sql = "UPDATE mydb.Emprestimo SET Usuario_Id_usuario = ?, Livro_ISBN = ?, Data_de_inicio = ?, Data_de_devolucao = ? WHERE Codigo_emprestimo = ?";

        try (Connection conexao = ConnectionDataBase.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            stmt.setString(2, livroISBN);
            stmt.setString(3, dataInicio);
            stmt.setString(4, dataDevolucao);
            stmt.setInt(5, codigoEmprestimo);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Empréstimo atualizado com sucesso!");
            } else {
                System.out.println("Empréstimo não encontrado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //delete
    public static void excluirEmprestimo(Scanner scanner) {
        System.out.println("Informe o Código do Empréstimo que deseja excluir: ");
        int codigoEmprestimo = scanner.nextInt();

        String sql = "DELETE FROM mydb.Emprestimo WHERE Codigo_emprestimo = ?";

        try (Connection conexao = ConnectionDataBase.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, codigoEmprestimo);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Empréstimo excluído com sucesso!");
            } else {
                System.out.println("Empréstimo não encontrado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}