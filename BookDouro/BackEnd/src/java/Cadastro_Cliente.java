package java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cadastro_Cliente {
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

    //VALIDA EMAIL
    public static boolean validaEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //VALIDA TELEFONE
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

    //VALIDA CPF
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
}