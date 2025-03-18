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




		usuarioDAO.inserirUsuario("Pedro", "PedraodaSilva@email.com","","","","","");

        // Adicione aqui qualquer lógica adicional que você precise

        scanner.close();
    }

	public static void cadastrarCliente(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Cadastro_Cliente cadastroCliente = new Cadastro_Cliente();
		Scanner scanner = new Scanner(System.in);

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
		System.out.println("Informe sua Função");
		cadastroCliente.setFuncao(scanner.nextLine());

		cadastroCliente.imprimirCadastro_Cliente();
		
		System.out.println("Tudo correto ? (S/N)");
		resposta = scanner.next().charAt(0);
		if (resposta == 'N') {
			alcapao = 'x';
		} else {
			usuarioDAO.inserirUsuario(cadastroCliente.getCPF(), cadastroCliente.getNome(), cadastroCliente.getEmail(),cadastroCliente.getTelefone() , cadastroCliente.getEndereco(), cadastroCliente.getSenha(), cadastroCliente.getFuncao());
		}
		}
		

	}

}