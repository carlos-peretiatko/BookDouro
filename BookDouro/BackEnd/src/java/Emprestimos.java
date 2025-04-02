package java;

public class Emprestimos {
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
}