package code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Livro {
    // Área de Variáveis
    private String ISBN;
    private String Titulo;
    private String Autor;
    private String Genero;
    private String Editora;
    private int numero_Exemplares;
    private int ano_Publicacao;

    // void imprimir o cadastro de livros
    public void imprimirCadastro_Livro() {
        System.out.println("ISBN....." + this.ISBN);
        System.out.println("Título....." + this.Titulo);
        System.out.println("Autor....." + this.Autor);
        System.out.println("Gênero....." + this.Genero);
        System.out.println("Editora....." + this.Editora);
        System.out.println("Número de Exemplares....." + this.numero_Exemplares);
        System.out.println("Ano de Publicação....." + this.ano_Publicacao);
    }

    // Área de Getters e Setters
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public String getEditora() {
        return Editora;
    }

    public void setEditora(String editora) {
        Editora = editora;
    }

    public int getNumero_Exemplares() {
        return numero_Exemplares;
    }

    public void setNumero_Exemplares(int numero_Exemplares) {
        this.numero_Exemplares = numero_Exemplares;
    }

    public int getAno_Publicacao() {
        return ano_Publicacao;
    }

    public void setAno_Publicacao(int ano_Publicacao) {
        this.ano_Publicacao = ano_Publicacao;
    }

    // outros metodos de Livro

    public static void cadastrarLivro(Scanner scanner) {
        LivroDAO livroDAO = new LivroDAO();
        Livro cadastroLivro = new Livro();

        char alcapao = 'x';
        char resposta = 'S';

        while (alcapao == 'x') {
            System.out.println("Informe o ISBN: ");
            cadastroLivro.setISBN(scanner.nextLine());
            System.out.println("Informe o Título: ");
            cadastroLivro.setTitulo(scanner.nextLine());
            System.out.println("Informe o Autor do livro: ");
            cadastroLivro.setAutor(scanner.nextLine());
            System.out.println("Informe o Gênero do livro: ");
            cadastroLivro.setGenero(scanner.nextLine());
            System.out.println("Informe a Editora: ");
            cadastroLivro.setEditora(scanner.nextLine());
            System.out.println("Informe o Número de Exemplares : ");
            cadastroLivro.setNumero_Exemplares(scanner.nextInt());
            System.out.println("Informe o Ano de Publicação do Livro: ");
            cadastroLivro.setAno_Publicacao(scanner.nextInt());

            cadastroLivro.imprimirCadastro_Livro();

            System.out.println("Tudo correto ? (S/N)");
            resposta = scanner.next().charAt(0);

            if (resposta == 'S') {
                LivroDAO.inserirLivro(

                        cadastroLivro.getISBN(),
                        cadastroLivro.getTitulo(),
                        cadastroLivro.getAutor(),
                        cadastroLivro.getGenero(),
                        cadastroLivro.getEditora(),
                        cadastroLivro.getNumero_Exemplares(),
                        cadastroLivro.getAno_Publicacao());

                System.out.println("Livro cadastrado com sucesso!");
                alcapao = 'y';
            } else if (resposta == 'N') {
                alcapao = 'x';
            } else {
                while (resposta != 'S' & resposta != 'N') {
                    System.out.println("Opção inválida. Tente novamente.");
                    resposta = scanner.next().charAt(0);
                }
            }
        }
    }

    // listar livros
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

    // editar
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

    // excluir
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