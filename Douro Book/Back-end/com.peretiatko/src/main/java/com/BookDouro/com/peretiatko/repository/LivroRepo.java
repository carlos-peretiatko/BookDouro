package com.BookDouro.com.peretiatko.repository;

import com.BookDouro.com.peretiatko.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepo extends JpaRepository<Livro, String> {
    Livro findByIsbn(String ISBN);
}