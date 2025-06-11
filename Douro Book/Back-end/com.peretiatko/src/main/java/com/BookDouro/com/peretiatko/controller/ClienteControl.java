package com.BookDouro.com.peretiatko.controller;

import com.BookDouro.com.peretiatko.model.Cliente;
import com.BookDouro.com.peretiatko.repository.ClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteControl {

    @Autowired
    private ClienteRepo clienteRepository;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{cpf}")
    public Cliente editarCliente(@PathVariable String cpf, @RequestBody Cliente cliente) {
        cliente.setCpf(cpf);
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/{cpf}")
    public void excluirCliente(@PathVariable String cpf) {
        clienteRepository.deleteById(cpf);
    }

    @GetMapping("/{cpf}")
    public Optional<Cliente> buscarCliente(@PathVariable String cpf) {
        return clienteRepository.findById(cpf);
    }
    
    
}