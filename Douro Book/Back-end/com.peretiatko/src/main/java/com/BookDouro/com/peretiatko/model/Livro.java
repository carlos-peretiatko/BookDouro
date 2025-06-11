package com.BookDouro.com.peretiatko.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Component
@Entity
public class Livro {

    @Id
	@Column(name = "ISBN")
    private String isbn;
	@Column(name = "Titulo")
    private String titulo;
	@Column(name = "Autor")
    private String autor;
	@Column(name = "Genero")
    private String genero;
	@Column(name = "Editora")
    private String editora;
	@Column(name = "Numero_de_exemplares")
    private int numero_de_exemplares;
	@Column(name = "Ano_de_publicacao")
    private int ano_de_publicacao;

    // Default constructor
    public Livro() {
    }

    // Constructor with all fields
    public Livro(String isbn, String titulo, String autor, String genero, 
                 String editora, int numero_de_exemplares, int ano_de_publicacao) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.editora = editora;
        this.numero_de_exemplares = numero_de_exemplares;
        this.ano_de_publicacao = ano_de_publicacao;
    }

    // Getters and Setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getNumero_de_exemplares() {
        return numero_de_exemplares;
    }

    public void setNumero_de_exemplares(int numero_de_exemplares) {
        this.numero_de_exemplares = numero_de_exemplares;
    }

    public int getAno_de_publicacao() {
        return ano_de_publicacao;
    }

    public void setAno_de_publicacao(int ano_de_publicacao) {
        this.ano_de_publicacao = ano_de_publicacao;
    }
}