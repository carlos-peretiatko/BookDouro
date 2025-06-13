package com.BookDouro.com.peretiatko.service;

import com.BookDouro.com.peretiatko.model.Livro;
import com.BookDouro.com.peretiatko.repository.LivroRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    private static final Logger logger = LoggerFactory.getLogger(LivroService.class);

    @Autowired
    private LivroRepo livroRepository;

    public List<Livro> getAllLivros() {
        logger.info("Buscando lista completa de livros");
        List<Livro> livros = livroRepository.findAll();
        logger.info("Total de livros encontrados: {}", livros.size());
        return livros;
    }

    public Livro getLivroById(String idStr) {
        logger.info("Tentando converter e buscar livro por ID: {}", idStr);
        try {
            Long id = Long.parseLong(idStr);
            Optional<Livro> livro = livroRepository.findById(id);
            
            if (livro.isPresent()) {
                logger.info("Livro encontrado: {}", livro.get());
                return livro.get();
            } else {
                logger.warn("Livro não encontrado com ID: {}", id);
                return null;
            }
        } catch (NumberFormatException e) {
            logger.error("Erro ao converter ID {} para Long", idStr, e);
            return null;
        }
    }

    public List<Livro> getLivrosByGenero(String genero) {
        logger.info("Buscando livros por gênero: {}", genero);
        List<Livro> livros = livroRepository.findByGenero(genero);
        logger.info("Encontrados {} livros do gênero {}", livros.size(), genero);
        return livros;
    }

    public Livro getLivroByIsbn(String isbn) {
        logger.info("Buscando livro por ISBN: {}", isbn);
        Optional<Livro> livro = livroRepository.findByIsbn(isbn);
        
        if (livro.isPresent()) {
            logger.info("Livro encontrado por ISBN: {}", livro.get());
            return livro.get();
        } else {
            logger.warn("Livro não encontrado com ISBN: {}", isbn);
            return null;
        }
    }
}