package com.BookDouro.com.peretiatko.controller;

import com.BookDouro.com.peretiatko.model.Livro;
import com.BookDouro.com.peretiatko.repository.LivroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
@CrossOrigin(origins = "http://localhost:8080")
public class LivroControl {

    @Autowired
    private LivroRepo livroRepository;

    @GetMapping("/categoria/{categoria}")
    public List<Livro> listarLivrosPorCategoria(@PathVariable String categoria) {
        return livroRepository.findByCategoria(categoria);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Livro> buscarLivroPorIsbn(@PathVariable String isbn) {
        return livroRepository.findById(isbn)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}