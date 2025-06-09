package com.BookDouro.com.peretiatko.controller;

import com.BookDouro.com.peretiatko.model.Emprestimo;
import com.BookDouro.com.peretiatko.repository.EmprestimoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoControl {

    @Autowired
    private EmprestimoRepo emprestimoRepository;

    @GetMapping
    public List<Emprestimo> listarEmprestimos() {
        return emprestimoRepository.findAll();
    }

    @PostMapping
    public Emprestimo cadastrarEmprestimo(@RequestBody Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }

    @PutMapping("/{id}")
    public Emprestimo editarEmprestimo(@PathVariable Integer id, @RequestBody Emprestimo emprestimo) {
        emprestimo.setCodigoEmprestimo(id);
        return emprestimoRepository.save(emprestimo);
    }

    @DeleteMapping("/{id}")
    public void excluirEmprestimo(@PathVariable Integer id) {
        emprestimoRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Optional<Emprestimo> buscarEmprestimo(@PathVariable Integer id) {
        return emprestimoRepository.findById(id);
    }
}