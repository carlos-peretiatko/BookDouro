package code;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("=== Sistema BookDouro ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Livro");
            System.out.println("3. Registrar Empréstimo");
            System.out.println("4. Listar Clientes");
            System.out.println("5. Listar Livros");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    cadastrarCliente(scanner);
                    break;
                case 2:
                    cadastrarLivro(scanner);
                    break;
                case 3:
                    cadastrarEmprestimo(scanner);
                    break;
                case 4:
                    listarUsuarios();
                    break;
                case 5:
                    listarLivros();
                    break;
                case 6:
                    System.out.println("Encerrando o sistema...");
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

	public static void cadastrarCliente(Scanner scanner){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Cadastro_Cliente cadastroCliente = new Cadastro_Cliente();

		char resposta = 'S';
		char alcapao = 'x';


		while (alcapao == 'x') {

			System.out.println("Informe seu CPF: ");
			cadastroCliente.setCPF(scanner.nextLine());
			System.out.println("Informe seu Nome de Usuario: ");
			cadastroCliente.setNome(scanner.nextLine());
			System.out.println("Informe seu Email: ");
			cadastroCliente.setEmail(scanner.nextLine());
			System.out.println("Informe seu telefone: ");
			cadastroCliente.setTelefone(scanner.nextLine());
			System.out.println("Informe Endereço: ");
			cadastroCliente.setEndereco(scanner.nextLine());
			System.out.println("Informe uma SENHA: ");
			cadastroCliente.setSenha(scanner.nextLine());
			System.out.println("Informe sua Função: ");
			cadastroCliente.setFuncao(scanner.nextLine());

			cadastroCliente.imprimirCadastro_Cliente();
		
			System.out.println("Tudo correto ? (S/N)");
			resposta = scanner.next().charAt(0);

			if (resposta == 'N') {
				alcapao = 'x';
			} else {

				//Valida se usuario colocou informações coerentes
				if (cadastroCliente.validaCPF(cadastroCliente.getCPF()) == false){
					while (cadastroCliente.validaCPF(cadastroCliente.getCPF()) == false){
						System.out.println("CPF invalido, digite novamente: ");
						cadastroCliente.setCPF(scanner.nextLine());
					}
				} else if (cadastroCliente.validaEmail(cadastroCliente.getEmail()) == false) {
					while (cadastroCliente.validaEmail(cadastroCliente.getEmail()) == false){
						System.out.println("Email invalido, digite novamente: ");
						cadastroCliente.setEmail(scanner.nextLine());
					}
				} else if (cadastroCliente.validaTelefone(cadastroCliente.getTelefone()) == false) {
					while (cadastroCliente.validaTelefone(cadastroCliente.getTelefone()) == false){
						System.out.println("Telefone invalido, digite novamente: ");
						cadastroCliente.setTelefone(scanner.nextLine());
					}
				} else{
				usuarioDAO.inserirUsuario(cadastroCliente.getCPF(), cadastroCliente.getNome(), cadastroCliente.getEmail(),cadastroCliente.getTelefone() , cadastroCliente.getEndereco(), cadastroCliente.getSenha(), cadastroCliente.getFuncao());

				System.out.println("Cliente cadastrado com sucesso!");
				alcapao = 'y';
				}
			}
		}
	}

	public static void cadastrarLivro(Scanner scanner) {
		LivroDAO livroDAO = new LivroDAO();
		Cadastro_Livro cadastroLivro = new Cadastro_Livro();

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
				LivroDAO.inserirUsuario(cadastroLivro.getISBN(), cadastroLivro.getTitulo(), cadastroLivro.getAutor(), cadastroLivro.getGenero() , cadastroLivro.getEditora(), cadastroLivro.getNumero_Exemplares(), cadastroLivro.getAno_Publicacao());
				System.out.println("Livro cadastrado com sucesso!");
				alcapao = 'y';
			} else if(resposta == 'N') {
				alcapao = 'x';
			} else {
				while (resposta != 'S' & resposta != 'N') {
					System.out.println("Opção inválida. Tente novamente.");
					resposta = scanner.next().charAt(0);
				}
			}
		}
	}

	//listar clientes
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
	
	//listar livros
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
}