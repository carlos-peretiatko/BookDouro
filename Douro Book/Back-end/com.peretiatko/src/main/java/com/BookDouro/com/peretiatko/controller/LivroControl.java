package com.BookDouro.com.peretiatko.controller;

import com.BookDouro.com.peretiatko.model.Livro;
import com.BookDouro.com.peretiatko.repository.LivroRepo;
import com.BookDouro.com.peretiatko.service.LivroService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/livros")
@CrossOrigin(origins = "*")
public class LivroControl {
    private static final Logger logger = LoggerFactory.getLogger(LivroControl.class);

    @Autowired
    private LivroService livroService;

    @Autowired
    private LivroRepo livroRepository;

    @GetMapping
    public ResponseEntity<List<Livro>> getAllLivros() {
        logger.info("Buscando todos os livros");
        try {
            List<Livro> livros = livroService.getAllLivros();
            logger.info("Total de livros encontrados: {}", livros.size());
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            logger.error("Erro ao buscar todos os livros", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLivroById(@PathVariable String id) {
        logger.info("Buscando livro por ID: {}", id);
        
        if (id == null || id.trim().isEmpty()) {
            logger.warn("ID inválido: {}", id);
            return ResponseEntity.badRequest().body("ID inválido");
        }

        try {
            Livro livro = livroService.getLivroById(id);
            if (livro == null) {
                logger.warn("Livro não encontrado com ID: {}", id);
                return ResponseEntity.notFound().build();
            }
            logger.info("Livro encontrado: {}", livro);
            return ResponseEntity.ok(livro);
        } catch (Exception e) {
            logger.error("Erro ao buscar livro por ID: {}", id, e);
            return ResponseEntity.internalServerError().body("Erro ao buscar livro: " + e.getMessage());
        }
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<?> getLivrosByGenero(@PathVariable String genero) {
        logger.info("Buscando livros por gênero: {}", genero);
        
        if (genero == null || genero.trim().isEmpty()) {
            logger.warn("Gênero inválido: {}", genero);
            return ResponseEntity.badRequest().body("Gênero inválido");
        }

        try {
            List<Livro> livros = livroRepository.findByGenero(genero);
            logger.info("Encontrados {} livros do gênero {}", livros.size(), genero);
            return ResponseEntity.ok(livros);
        } catch (Exception e) {
            logger.error("Erro ao buscar livros por gênero: {}", genero, e);
            return ResponseEntity.internalServerError().body("Erro ao buscar livros: " + e.getMessage());
        }
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<?> getLivroByIsbn(@PathVariable String isbn) {
        logger.info("Buscando livro por ISBN: {}", isbn);
        
        if (isbn == null || isbn.trim().isEmpty()) {
            logger.warn("ISBN inválido: {}", isbn);
            return ResponseEntity.badRequest().body("ISBN inválido");
        }

        try {
            Livro livro = livroService.getLivroByIsbn(isbn);
            if (livro == null) {
                logger.warn("Livro não encontrado com ISBN: {}", isbn);
                return ResponseEntity.notFound().build();
            }
            logger.info("Livro encontrado: {}", livro);
            return ResponseEntity.ok(livro);
        } catch (Exception e) {
            logger.error("Erro ao buscar livro por ISBN: {}", isbn, e);
            return ResponseEntity.internalServerError().body("Erro ao buscar livro: " + e.getMessage());
        }
    }

    @GetMapping("/destaques")
    public ResponseEntity<?> getDestaques() {
        logger.info("Buscando livros em destaque");
        try {
            Map<String, List<Livro>> destaques = Map.of(
                "Ficção", livroRepository.findByGeneroOrderByAnoDePublicacaoDesc("Ficção"),
                "Romance", livroRepository.findByGeneroOrderByAnoDePublicacaoDesc("Romance"),
                "Terror", livroRepository.findByGeneroOrderByAnoDePublicacaoDesc("Terror"),
                "Técnico", livroRepository.findByGeneroOrderByAnoDePublicacaoDesc("Técnico")
            );
            
            destaques.forEach((genero, livros) -> 
                logger.info("Encontrados {} livros do gênero {}", livros.size(), genero));
                
            return ResponseEntity.ok(destaques);
        } catch (Exception e) {
            logger.error("Erro ao buscar destaques", e);
            return ResponseEntity.internalServerError().body("Erro ao buscar destaques: " + e.getMessage());
        }
    }
}