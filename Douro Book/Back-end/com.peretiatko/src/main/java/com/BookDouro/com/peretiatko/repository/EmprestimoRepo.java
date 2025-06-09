package com.BookDouro.com.peretiatko.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BookDouro.com.peretiatko.model.Emprestimo;

public interface EmprestimoRepo extends JpaRepository<Emprestimo, Integer> {
	//Emprestimo findBy... (type ..., type ...)
}
