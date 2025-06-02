package com.BookDouro.com.peretiatko.repository;

import com.BookDouro.com.peretiatko.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepo extends JpaRepository<Cliente, String> {
    Cliente findByCpfAndSenha(String cpf, String senha);
}