package com.BookDouro.com.peretiatko.controller;

import com.BookDouro.com.peretiatko.model.Avaliacao;
import com.BookDouro.com.peretiatko.model.Cliente;
import com.BookDouro.com.peretiatko.model.Livro;
import com.BookDouro.com.peretiatko.repository.AvaliacaoRepository;
import com.BookDouro.com.peretiatko.repository.ClienteRepo;
import com.BookDouro.com.peretiatko.repository.LivroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/avaliacoes")
@CrossOrigin(origins = "*")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private LivroRepo livroRepository;

    @Autowired
    private ClienteRepo clienteRepository;

    @PostMapping("/livro/{livroId}")
    public ResponseEntity<?> criarAvaliacao(@PathVariable String livroId, @RequestBody Map<String, Object> dados) {
        try {
            Long id = Long.parseLong(livroId);
            Optional<Livro> livro = livroRepository.findById(id);
            if (livro.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Optional<Cliente> usuario = clienteRepository.findById((Integer) dados.get("usuarioId"));
            if (usuario.isEmpty()) {
                return ResponseEntity.badRequest().body("Usuário não encontrado");
            }

            Avaliacao avaliacao = new Avaliacao();
            avaliacao.setLivro(livro.get());
            avaliacao.setUsuario(usuario.get());
            avaliacao.setRating((Integer) dados.get("rating"));
            avaliacao.setComentario((String) dados.get("comentario"));

            return ResponseEntity.ok(avaliacaoRepository.save(avaliacao));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("ID do livro inválido");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao criar avaliação: " + e.getMessage());
        }
    }

    @GetMapping("/livro/{livroId}")
    public ResponseEntity<?> getAvaliacoesPorLivro(@PathVariable String livroId) {
        try {
            Long id = Long.parseLong(livroId);
            List<Avaliacao> avaliacoes = avaliacaoRepository.findByLivroId(id);
            return ResponseEntity.ok(avaliacoes);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("ID do livro inválido");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao buscar avaliações: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAvaliacao(@PathVariable Integer id, @RequestBody Map<String, Object> dados) {
        Optional<Avaliacao> avaliacaoExistente = avaliacaoRepository.findById(id);
        if (avaliacaoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Avaliacao avaliacao = avaliacaoExistente.get();
        avaliacao.setRating((Integer) dados.get("rating"));
        avaliacao.setComentario((String) dados.get("comentario"));

        return ResponseEntity.ok(avaliacaoRepository.save(avaliacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarAvaliacao(@PathVariable Integer id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);
        if (avaliacao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        avaliacaoRepository.delete(avaliacao.get());
        return ResponseEntity.noContent().build();
    }
}