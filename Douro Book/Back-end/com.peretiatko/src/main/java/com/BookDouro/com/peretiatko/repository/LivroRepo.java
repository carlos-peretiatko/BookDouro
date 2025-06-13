package com.BookDouro.com.peretiatko.repository;

import com.BookDouro.com.peretiatko.model.Livro;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LivroRepo extends JpaRepository<Livro, Long> {
    
    @Query("SELECT l FROM Livro l WHERE l.genero = :genero")
    List<Livro> findByGenero(@Param("genero") String genero);
    
    @Query("SELECT l FROM Livro l WHERE l.genero = :genero ORDER BY l.anoDePublicacao DESC")
    List<Livro> findByGeneroOrderByAnoDePublicacaoDesc(@Param("genero") String genero);
    
    @Query("SELECT l FROM Livro l WHERE l.isbn = :isbn")
    Optional<Livro> findByIsbn(@Param("isbn") String isbn);
    
    @Query("SELECT l FROM Livro l WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    List<Livro> findByTituloContainingIgnoreCase(@Param("titulo") String titulo);
    
    @Query("SELECT l FROM Livro l WHERE LOWER(l.autor) LIKE LOWER(CONCAT('%', :autor, '%'))")
    List<Livro> findByAutorContainingIgnoreCase(@Param("autor") String autor);
    
    @Query("SELECT l FROM Livro l WHERE LOWER(l.genero) LIKE LOWER(CONCAT('%', :genero, '%'))")
    List<Livro> findByGeneroContainingIgnoreCase(@Param("genero") String genero);
    
    @Query("SELECT l FROM Livro l WHERE " +
           "LOWER(l.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(l.autor) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(l.genero) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(l.isbn) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Livro> findBySearchTerm(@Param("termo") String termo);
}
