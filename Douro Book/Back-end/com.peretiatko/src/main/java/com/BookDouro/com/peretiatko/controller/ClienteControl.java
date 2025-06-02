package com.BookDouro.com.peretiatko.controller;

import java.util.List;

import com.BookDouro.com.peretiatko.model.Cliente;
import com.BookDouro.com.peretiatko.repository.ClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class ClienteControl {
	@Autowired
    private ClienteRepo clienteRepo;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepo.findAll();
    }

    @PostMapping
    public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    @GetMapping("/login")
    public Cliente login(@RequestParam String cpf, @RequestParam String senha) {
        return clienteRepo.findByCpfAndSenha(cpf, senha);
    }

    // Adicione métodos para editar e excluir conforme necessário
}
