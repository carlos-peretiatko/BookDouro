package bookdouro;

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