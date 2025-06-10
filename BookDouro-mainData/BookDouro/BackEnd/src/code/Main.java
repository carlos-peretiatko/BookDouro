package code;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		// Exigir login antes de acessar o sistema
		boolean loggedIn = false;
		while (!loggedIn) {
			System.out.println("=== Sistema BookDouro ===");
			System.out.println("1. Login");
			System.out.println("2. Cadastrar Cliente");
			System.out.println("3. Sair");
			System.out.print("Escolha uma opção: ");

			int opcao = scanner.nextInt();
			scanner.nextLine(); // Limpa o buffer

			switch (opcao) {
				case 1:
					loggedIn = Cliente.loginUsuario(scanner);
					break;
				case 2:
					Cliente.cadastrarCliente(scanner);
					break;
				case 3:
					System.out.println("Encerrando o sistema...");
					running = false;
					return; // Sai do programa
				default:
					System.out.println("Opção inválida. Tente novamente.");
			}
		}

		// Menu principal após login
		while (running) {
			System.out.println("=== Sistema BookDouro ===");
			System.out.println("1. Cadastrar Cliente");
			System.out.println("2. Cadastrar Livro");
			System.out.println("3. Registrar Empréstimo");
			System.out.println("4. Listar Clientes");
			System.out.println("5. Listar Livros");
			System.out.println("6. Listar Empréstimos");
			System.out.println("7. Editar Cliente");
			System.out.println("8. Editar Livro");
			System.out.println("9. Excluir Cliente");
			System.out.println("10. Excluir Livro");
			System.out.println("11. Excluir Empréstimo");
			System.out.println("12. Sair");
			System.out.print("Escolha uma opção: ");

			int opcao = scanner.nextInt();
			scanner.nextLine(); // Limpa o buffer

			//usar classes DAO para CRUD e normais para inserir,
			//fazendo relacionamento entre a normal e a DAO

			switch (opcao) {
                case 1:
                    Cliente.cadastrarCliente(scanner);
                    break;
                case 2:
                    Livro.cadastrarLivro(scanner);
                    break;
                case 3:
                    Emprestimo.cadastrarEmprestimo(scanner);
                    break;
                case 4:
                    UsuarioDAO.listarUsuarios();
                    break;
                case 5:
                    LivroDAO.listarLivros();
                    break;
                case 6:
                    EmprestimoDAO.listarEmprestimos();
                    break;
                case 7:
                    UsuarioDAO.editarCliente(scanner);
                    break;
                case 8:
                    LivroDAO.editarLivro(scanner);
                    break;
                case 9:
                    UsuarioDAO.excluirCliente(scanner);
                    break;
                case 10:
                    LivroDAO.excluirLivro(scanner);
                    break;
                case 11:
                    EmprestimoDAO.excluirEmprestimo(scanner);
                    break;
                case 12:
                    System.out.println("Encerrando o sistema...");
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
		}
		scanner.close();
	}
}