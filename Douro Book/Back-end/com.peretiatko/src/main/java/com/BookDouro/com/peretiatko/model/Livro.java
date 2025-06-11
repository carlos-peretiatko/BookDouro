package com.BookDouro.com.peretiatko.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Component
@Entity
public class Livro {

	@Id
	private String isbn;
	private String titulo;
	private String autor;
	private String categoria;
	private String coverUrl;
	private String descricao;
	private Boolean disponivel;

	// cosntructors
	public Livro() {

	}

	public Livro(String isbn, String titulo, String autor, String categoria, String coverUrl, String descricao,
			Boolean disponivel) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.categoria = categoria;
		this.coverUrl = coverUrl;
		this.descricao = descricao;
		this.disponivel = disponivel;
	}

	// gets e setts
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}
}
