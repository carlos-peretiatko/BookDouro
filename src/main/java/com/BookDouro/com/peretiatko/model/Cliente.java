package com.BookDouro.com.peretiatko.model;

import org.springframework.stereotype.Component;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "Usuario")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_usuario", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "CPF_usuario", unique = true, nullable = false)
    private String cpf;

    @Column(name = "Nome_usuario", nullable = false)
    private String nome;

    @Column(name = "Endereco_usuario", nullable = false)
    private String endereco;

    @Column(name = "Telefone_usuario", nullable = false)
    private String telefone;

    @Column(name = "Email_usuario", unique = true, nullable = false)
    private String email;

    @Column(name = "Funcao", nullable = false)
    private String funcao;

    @Column(name = "Senha_usuario", nullable = false)
    private String senha;
    
    public Cliente() {}
    
    public Cliente(String cpf, String nome, String endereco, String telefone, String email, String funcao, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.funcao = funcao;
        this.senha = senha;
    }
    
    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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