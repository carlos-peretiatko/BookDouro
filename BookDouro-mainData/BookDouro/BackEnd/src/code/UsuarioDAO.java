package code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UsuarioDAO {

    // CRUD

    // Create
    public static void inserirUsuario(String CPF_usuario, String Nome_usuario, String Email_usuario,
            String Telefone_usuario, String Endereco_usuario, String Senha_usuario, String Funcao) {
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

    // Read
    public static void listarUsuarios() {
        String sql = "SELECT * FROM mydb.Usuario";

        try (Connection conexao = ConnectionDataBase.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                var rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("CPF: " + rs.getString("CPF_usuario"));
                System.out.println("Nome: " + rs.getString("Nome_usuario"));
                System.out.println("Email: " + rs.getString("Email_usuario"));
                System.out.println("Telefone: " + rs.getString("Telefone_usuario"));
                System.out.println("Endereço: " + rs.getString("Endereco_usuario"));
                System.out.println("Senha" + rs.getString("Senha_usuario"));
                System.out.println("Função: " + rs.getString("Funcao"));
                System.out.println("------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update
    public static void editarCliente(Scanner scanner) {
        System.out.println("Informe o CPF do cliente que deseja editar: ");
        String cpf = scanner.nextLine();

        System.out.println("Informe o novo Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Informe o novo Email: ");
        String email = scanner.nextLine();
        System.out.println("Informe o novo Telefone: ");
        String telefone = scanner.nextLine();
        System.out.println("Informe o novo Endereço: ");
        String endereco = scanner.nextLine();
        System.out.println("Informe a nova Função: ");
        String funcao = scanner.nextLine();

        String sql = "UPDATE mydb.Usuario SET Nome_usuario = ?, Email_usuario = ?, Telefone_usuario = ?, Endereco_usuario = ?, Funcao = ? WHERE CPF_usuario = ?";

        try (Connection conexao = ConnectionDataBase.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, telefone);
            stmt.setString(4, endereco);
            stmt.setString(5, funcao);
            stmt.setString(6, cpf);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cliente atualizado com sucesso!");
            } else {
                System.out.println("Cliente não encontrado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public static void excluirCliente(Scanner scanner) {
        System.out.println("Informe o CPF do cliente que deseja excluir: ");
        String cpf = scanner.nextLine();

        String sql = "DELETE FROM mydb.Usuario WHERE CPF_usuario = ?";

        try (Connection conexao = ConnectionDataBase.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cliente excluído com sucesso!");
            } else {
                System.out.println("Cliente não encontrado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🟩🟩🟩🟩🟩
    // 🟩⬛🟩⬛🟩
    // 🟩🟩⬛🟩🟩
    // 🟩⬛⬛⬛🟩
    // 🟩⬛🟩⬛🟩
    // ⬜🟩🟩🟩⬜
    // ⬜🟩🟩🟩⬜
    // ⬜🟩🟩🟩⬜
    // ⬜🟩🟩🟩⬜
    // ⬜🟩🟩🟩⬜
    // 🟩🟩🟩🟩🟩
    // 🟩🟩⬜🟩🟩

}