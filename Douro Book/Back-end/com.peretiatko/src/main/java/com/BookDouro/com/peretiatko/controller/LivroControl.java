package com.BookDouro.com.peretiatko.controller;

import com.BookDouro.com.peretiatko.model.Livro;
import com.BookDouro.com.peretiatko.repository.LivroRepo;
import com.BookDouro.com.peretiatko.service.LivroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
@CrossOrigin(origins = "*")
public class LivroControl {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<Livro> getAllLivros() {
        return livroService.getAllLivros();
    }

    @GetMapping("/{isbn}")
    public Livro getLivroByIsbn(@PathVariable String isbn) {
        return livroService.getLivroByIsbn(isbn);
    }
    
}