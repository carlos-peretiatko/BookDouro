package com.BookDouro.com.peretiatko.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cliente {
    @Id
    @Column(name = "Id_usuario")
    private int id;
    @Column(name = "CPF_usuario")
    private String cpf;
    @Column(name = "Nome_usuario")
    private String nome;
    @Column(name = "Endereco_usuario")
    private String endereco;
    @Column(name = "Telefone_usuario")
    private String telefone;
    @Column(name = "Email_usuario")
    private String email;
    @Column(name = "Funcao")
    private String funcao;
    @Column(name = "Senha_usuario")
    private String senha;
	
    // getters e setters
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	} 
}