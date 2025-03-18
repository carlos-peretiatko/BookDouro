package bookdouro;

public class Cadastro_Livro {
    // Área de Variáveis
    private String Titulo;
    private String Autor;
    private String Genero;
    private String Editora;
    private String numero_Exemplares;
    private String ano_Publicacao;

    // void imprimir o cadastro de livros
    public void imprimirCadastro_Livro() {
        System.out.println("Título....." + this.Titulo);
        System.out.println("Autor....." + this.Autor);
        System.out.println("Genero....." + this.Genero);
        System.out.println("Editora....." + this.Editora);
        System.out.println("Número de Exemplares....." + this.numero_Exemplares);
        System.out.println("Ano de Publicação....." + this.ano_Publicacao);
    }

    // Área de Getters e Setters
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

    public String getNumero_Exemplares() {
        return numero_Exemplares;
    }

    public void setNumero_Exemplares(String numero_Exemplares) {
        this.numero_Exemplares = numero_Exemplares;
    }

    public String getAno_Publicacao() {
        return ano_Publicacao;
    }

    public void setAno_Publicacao(String ano_Publicacao) {
        this.ano_Publicacao = ano_Publicacao;
    }
}