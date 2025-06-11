package com.BookDouro.com.peretiatko.repository;

import com.BookDouro.com.peretiatko.model.Livro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepo extends JpaRepository<Livro, String> {
	//List<Livro> findByCategoria(String categoria);
}
