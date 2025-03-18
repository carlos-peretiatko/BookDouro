package bookdouro;

import java.util.Scanner;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cadastro_Cliente CD1 = new Cadastro_Cliente();
        Cadastro_Livro CL1 = new Cadastro_Livro();
        Emprestimos EP1 = new Emprestimos();
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		//cadastrarCliente(scanner);

        // Adicione aqui qualquer lógica adicional que você precise

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

	public static void cadastrarEmprestimo() {
		
		//Fazer no mesmo modelo que os acima

	}
}