package com.BookDouro.com.peretiatko.repository;

import com.BookDouro.com.peretiatko.model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
    List<Favorito> findByUsuarioId(Integer usuarioId);
    Optional<Favorito> findByUsuarioIdAndLivroId(Integer usuarioId, Long livroId);
    boolean existsByUsuarioIdAndLivroId(Integer usuarioId, Long livroId);
}