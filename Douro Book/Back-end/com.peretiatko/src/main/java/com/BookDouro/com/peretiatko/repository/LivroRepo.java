package com.BookDouro.com.peretiatko.repository;

import com.BookDouro.com.peretiatko.model.Livro;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepo extends JpaRepository<Livro, Long> {
    List<Livro> findByGenero(String genero);
    List<Livro> findByGeneroOrderByAnoDePublicacaoDesc(String genero);
    Optional<Livro> findByIsbn(String isbn);
}
