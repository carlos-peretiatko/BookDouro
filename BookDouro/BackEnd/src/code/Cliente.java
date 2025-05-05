package code;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Cliente {
    // Variáveis da Classe
    private String CPF;
    private String Nome;
    private String Endereco;
    private String Telefone;
    private String Email;
    private String Funcao;
    private String Senha;

    // void Cadastro de Clientes
    public void imprimirCadastro_Cliente() {
        System.out.println("CPF....." + this.CPF);
        System.out.println("Nome....." + this.Nome);
        System.out.println("Endereço....." + this.Endereco);
        System.out.println("Telefone....." + this.Telefone);
        System.out.println("E-Mail....." + this.Email);
        System.out.println("Função....." + this.Funcao);
        System.out.println("Senha....." + this.Senha);
    }

    // VALIDA EMAIL
    public static boolean validaEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // VALIDA TELEFONE
    public static boolean validaTelefone(String telefone) {
        // Remove todos os caracteres não numéricos
        telefone = telefone.replaceAll("[^0-9]", "");

        // Verifica se tem exatamente 11 dígitos
        if (telefone.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        if (telefone.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Verifica se o DDD é válido (entre 11 e 99)
        int ddd = Integer.parseInt(telefone.substring(0, 2));
        if (ddd < 11 || ddd > 99) {
            return false;
        }

        // Verifica se o nono dígito é 9
        if (telefone.charAt(2) != '9') {
            return false;
        }

        return true;
    }

    // VALIDA CPF
    public static boolean validaCPF(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se o CPF é uma sequência de números iguais (como 111.111.111-11)
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Valida o primeiro dígito verificador -- primeiro dos ultimos
        int PrimeiroDigito = calculateDigit(cpf.substring(0, 9), 10);
        if (PrimeiroDigito != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        // Valida o segundo dígito verificador -- segundo dos ultimos
        int SegundoDigito = calculateDigit(cpf.substring(0, 10), 11);
        if (SegundoDigito != Character.getNumericValue(cpf.charAt(10))) {
            return false;
        }

        return true;
    }

    // Função para calcular um dígito verificador -- os dois ultimos numeros
    private static int calculateDigit(String base, int weight) {
        int sum = 0;
        for (int i = 0; i < base.length(); i++) {
            sum += Character.getNumericValue(base.charAt(i)) * weight--;
        }

        int remainder = sum % 11;
        return (remainder < 2) ? 0 : 11 - remainder;
    }

    // Getters e Setters da Classe
    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cPF) {
        CPF = cPF;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFuncao() {
        return Funcao;
    }

    public void setFuncao(String funcao) {
        Funcao = funcao;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

        // ⠀⠀⠀⠀⠀⠀⠀⢀⣠⠤⠒⠒⠒⢛⣒⠒⠒⠦⢤⣀⠀⠀⠀⠀
        // ⠀⣤⡀⠀⢀⡴⠚⠁⠀⠀⠀⡠⢊⠵⡈⡆⠀⠀⠀⠈⢱⠆⠀⠀
        // ⡜⡷⢱⠔⠁⠀⠀⠀⠀⠀⠊⠰⠇⣀⡇⡇⢀⠀⢀⣠⠏⠀⠀⠀
        // ⣇⣧⡋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠇⠈⠙⠛⢤⡀⠀⠀⠀
        // ⢘⡏⠀⠀⠀⠀⠀⠰⠊⢀⡠⢄⡀⠀⠀⠀⠀⠀⠀⠀⠙⢦⠀⠀
        // ⢸⢰⠋⢢⠀⠀⠀⠀⡔⢁⣤⡀⠹⡀⠀⠀⠀⠀⠀⠀⠀⠀⢳⡀
        // ⠈⡇⢰⡟⢳⡀⢀⠎⠀⡿⣄⣼⠀⠇⠀⠀⠀⠀⠠⡄⠀⠀⠀⣳
        // ⠀⠰⡸⣽⣿⣈⣁⣀⠀⠙⠿⢃⡼⠲⡀⠀⠀⠀⠀⠹⡍⠉⠉⠁
        // ⠀⠀⢵⠒⠋⠿⠗⠀⠉⠉⠉⡩⠀⢀⡇⠀⠀⠀⠀⠀⢧⠀⠀⠀
        // ⠀⠀⠘⠢⣄⡀⠀⠀⠀⠀⣉⣠⠴⠋⠀⣀⣀⡀⠀⠀⡞⠀⠀⠀
        // ⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠉⠑⠦⠃⠀⠀⠀
    
        //login e cadastro I
        //                 V

    // metodo para cadastrar cliente
    public static void cadastrarCliente(Scanner scanner) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Cliente Cliente = new Cliente();

        char resposta = 'S';
        char alcapao = 'x';

        while (alcapao == 'x') {

            System.out.println("Informe seu CPF: ");
            Cliente.setCPF(scanner.nextLine());
            System.out.println("Informe seu Nome de Usuario: ");
            Cliente.setNome(scanner.nextLine());
            System.out.println("Informe seu Email: ");
            Cliente.setEmail(scanner.nextLine());
            System.out.println("Informe seu telefone: ");
            Cliente.setTelefone(scanner.nextLine());
            System.out.println("Informe Endereço: ");
            Cliente.setEndereco(scanner.nextLine());
            System.out.println("Informe uma SENHA: ");
            Cliente.setSenha(scanner.nextLine());
            System.out.println("Informe sua Função: ");
            Cliente.setFuncao(scanner.nextLine());

            Cliente.imprimirCadastro_Cliente();

            System.out.println("Tudo correto ? (S/N)");
            resposta = scanner.next().charAt(0);

            if (resposta == 'N') {
                alcapao = 'x';
            } else {

                // Valida se usuario colocou informações coerentes
                if (Cliente.validaCPF(Cliente.getCPF()) == false) {
                    while (Cliente.validaCPF(Cliente.getCPF()) == false) {
                        System.out.println("CPF invalido, digite novamente: ");
                        Cliente.setCPF(scanner.nextLine());
                    }
                } else if (Cliente.validaEmail(Cliente.getEmail()) == false) {
                    while (Cliente.validaEmail(Cliente.getEmail()) == false) {
                        System.out.println("Email invalido, digite novamente: ");
                        Cliente.setEmail(scanner.nextLine());
                    }
                } else if (Cliente.validaTelefone(Cliente.getTelefone()) == false) {
                    while (Cliente.validaTelefone(Cliente.getTelefone()) == false) {
                        System.out.println("Telefone invalido, digite novamente: ");
                        Cliente.setTelefone(scanner.nextLine());
                    }
                } else {
                    usuarioDAO.inserirUsuario(Cliente.getCPF(), Cliente.getNome(),
                            Cliente.getEmail(),
                            Cliente.getTelefone(), Cliente.getEndereco(), Cliente.getSenha(),
                            Cliente.getFuncao());

                    System.out.println("Cliente cadastrado com sucesso!");
                    alcapao = 'y';
                }
            }
        }
    }
    
    // login
    public static boolean loginUsuario(Scanner scanner) {
        System.out.println("Informe seu CPF: ");
        String cpf = scanner.nextLine();

        System.out.println("Informe sua senha: ");
        String senha = scanner.nextLine();

        String sql = "SELECT * FROM mydb.Usuario WHERE CPF_usuario = ? AND Senha_usuario = ?";

        try (Connection conexao = ConnectionDataBase.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Login bem-sucedido! Bem-vindo, " + rs.getString("Nome_usuario") + "!");
                    return true;
                } else {
                    System.out.println("CPF ou senha incorretos. Tente novamente.");
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}