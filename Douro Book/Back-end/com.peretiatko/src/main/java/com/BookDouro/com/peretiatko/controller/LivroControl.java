package com.BookDouro.com.peretiatko.controller;

import com.BookDouro.com.peretiatko.model.Livro;
import com.BookDouro.com.peretiatko.repository.LivroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/livros")
public class LivroControl {

    @Autowired
    private LivroRepo livroRepository;

    @GetMapping
    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    @PostMapping
    public Livro cadastrarLivro(@RequestBody Livro livro) {
        return livroRepository.save(livro);
    }

    @GetMapping("/{isbn}")
    public Optional<Livro> buscarLivro(@PathVariable String isbn) {
        return livroRepository.findById(isbn);
    }

    @PutMapping("/{isbn}")
    public Livro editarLivro(@PathVariable String isbn, @RequestBody Livro livro) {
        livro.setISBN(isbn);
        return livroRepository.save(livro);
    }

    @DeleteMapping("/{isbn}")
    public void excluirLivro(@PathVariable String isbn) {
        livroRepository.deleteById(isbn);
    }
}