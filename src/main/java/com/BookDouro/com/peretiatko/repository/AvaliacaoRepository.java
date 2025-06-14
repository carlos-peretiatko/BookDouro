package com.BookDouro.com.peretiatko.repository;

import com.BookDouro.com.peretiatko.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
    List<Avaliacao> findByLivroId(Long livroId);
}