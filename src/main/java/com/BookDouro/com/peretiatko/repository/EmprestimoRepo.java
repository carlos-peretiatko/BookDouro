package com.BookDouro.com.peretiatko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BookDouro.com.peretiatko.model.Emprestimo;

@Repository
public interface EmprestimoRepo extends JpaRepository<Emprestimo, Integer> {
    List<Emprestimo> findByUsuarioId(Integer usuarioId);
}