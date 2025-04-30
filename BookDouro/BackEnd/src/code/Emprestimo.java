package code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Emprestimo {
    // Variáveis da Classe
    private String Data_inicio;
    private String Data_Devolucao;

    // Public void imprimir os Empréstimos
    public void imprimirEmprestimos() {
        System.out.println("Data de Início....." + this.Data_inicio);
        System.out.println("Data da devolução do Livro....." + this.Data_Devolucao);
    }

    // Área de Getters e Setters da Classe
    public String getData_inicio() {
        return Data_inicio;
    }

    public void setData_inicio(String data_inicio) {
        Data_inicio = data_inicio;
    }

    public String getData_Devolucao() {
        return Data_Devolucao;
    }

    public void setData_Devolucao(String data_Devolucao) {
        Data_Devolucao = data_Devolucao;
    }

    // cadastros
    public static void cadastrarEmprestimo(Scanner scanner) {
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

        char alcapao = 'x';
        char resposta = 'S';

        while (alcapao == 'x') {
            System.out.println("Informe o ID do Usuário: ");
            int usuarioId = Integer.parseInt(scanner.nextLine());

            System.out.println("Informe o ISBN do Livro: ");
            String livroISBN = scanner.nextLine();

            System.out.println("Informe a Data de Início (AAAA-MM-DD): ");
            String dataInicio = scanner.nextLine();

            System.out.println("Informe a Data de Devolução (AAAA-MM-DD): ");
            String dataDevolucao = scanner.nextLine();

            // Exibe os dados para confirmação
            System.out.println("Usuário: " + usuarioId);
            System.out.println("Livro ISBN: " + livroISBN);
            System.out.println("Data de Início: " + dataInicio);
            System.out.println("Data de Devolução: " + dataDevolucao);

            System.out.println("Tudo correto? (S/N)");
            resposta = scanner.next().charAt(0);
            scanner.nextLine(); // Limpa o buffer

            if (resposta == 'S' || resposta == 's') {
                EmprestimoDAO.inserirEmprestimo(usuarioId, livroISBN, dataInicio, dataDevolucao);
                System.out.println("Empréstimo cadastrado com sucesso!");
                alcapao = 'y';
            } else if (resposta == 'N' || resposta == 'n') {
                alcapao = 'x';
            } else {
                while (resposta != 'S' && resposta != 's' && resposta != 'N' && resposta != 'n') {
                    System.out.println("Opção inválida. Tente novamente (S/N).");
                    resposta = scanner.next().charAt(0);
                    scanner.nextLine();
                }
            }
        }
    }

    // ler
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

    // excluir
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
