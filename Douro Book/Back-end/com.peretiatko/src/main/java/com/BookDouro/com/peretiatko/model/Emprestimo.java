package com.BookDouro.com.peretiatko.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Component
@Entity
public class Emprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Codigo_emprestimo")
	private Integer codigoEmprestimo; // PK auto increment
	@Column(name = "Usuario_Id_usuario")
	private Integer usuarioId; // FK para Usuario (auto increment no banco)
	@Column(name = "Livro_ISBN")
	private String livroISBN; // FK para Livro
	@Column(name = "Data_de_inicio")
	private String dataInicio;
	@Column(name = "Data_de_devolução")
	private String dataDevolucao;

	//cosntructors
	public Emprestimo() {
		
	}
	
	public Emprestimo(Integer codigoEmprestimo, Integer usuarioId, String livroISBN, String dataInicio,
			String dataDevolucao) {
		super();
		this.codigoEmprestimo = codigoEmprestimo;
		this.usuarioId = usuarioId;
		this.livroISBN = livroISBN;
		this.dataInicio = dataInicio;
		this.dataDevolucao = dataDevolucao;
	}



	// Área de Getters e Setters da Classe
	public Integer getCodigoEmprestimo() {
		return codigoEmprestimo;
	}

	public void setCodigoEmprestimo(Integer codigoEmprestimo) {
		this.codigoEmprestimo = codigoEmprestimo;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getLivroISBN() {
		return livroISBN;
	}

	public void setLivroISBN(String livroISBN) {
		this.livroISBN = livroISBN;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

}
