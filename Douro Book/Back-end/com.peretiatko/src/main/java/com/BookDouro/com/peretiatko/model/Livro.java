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
	private String ISBN;
	@Column(name = "Titulo")
	private String Titulo;
	@Column(name = "Autor")
	private String Autor;
	@Column(name = "Genero")
	private String Genero;
	@Column(name = "Editora")
	private String Editora;
	@Column(name = "Numero_de_exemplares")
	private int numero_Exemplares;
	@Column(name = "Ano_de_publicacao")
	private int ano_Publicacao;

	// gets e setts
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

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

	public int getNumero_Exemplares() {
		return numero_Exemplares;
	}

	public void setNumero_Exemplares(int numero_Exemplares) {
		this.numero_Exemplares = numero_Exemplares;
	}

	public int getAno_Publicacao() {
		return ano_Publicacao;
	}

	public void setAno_Publicacao(int ano_Publicacao) {
		this.ano_Publicacao = ano_Publicacao;
	}

}
