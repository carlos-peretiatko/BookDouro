package com.BookDouro.com.peretiatko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.BookDouro.com.peretiatko.repository.LivroRepo;
import com.BookDouro.com.peretiatko.model.Livro;
import java.util.List;

@Service
public class LivroService {
    
    @Autowired
    private LivroRepo livroRepository;
    
    public List<Livro> getAllLivros() {
        return livroRepository.findAll();
    }
    
    public Livro getLivroByIsbn(String isbn) {
        return livroRepository.findById(isbn).orElse(null);
    }
}