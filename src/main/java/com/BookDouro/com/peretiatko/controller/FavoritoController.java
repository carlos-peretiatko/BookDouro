package com.BookDouro.com.peretiatko.controller;

import com.BookDouro.com.peretiatko.model.Favorito;
import com.BookDouro.com.peretiatko.model.Cliente;
import com.BookDouro.com.peretiatko.model.Livro;
import com.BookDouro.com.peretiatko.repository.FavoritoRepository;
import com.BookDouro.com.peretiatko.repository.ClienteRepo;
import com.BookDouro.com.peretiatko.repository.LivroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios/favoritos")
@CrossOrigin(origins = "*")
public class FavoritoController {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private ClienteRepo clienteRepository;

    @Autowired
    private LivroRepo livroRepository;

    @GetMapping("/{livroId}")
    public ResponseEntity<Boolean> verificarFavorito(@PathVariable Long livroId) {
        Integer usuarioId = 1; // TODO: Pegar o ID do usuário logado
        return ResponseEntity.ok(favoritoRepository.existsByUsuarioIdAndLivroId(usuarioId, livroId));
    }

    @PostMapping("/{livroId}")
    public ResponseEntity<?> adicionarFavorito(@PathVariable Long livroId) {
        try {
            Integer usuarioId = 1; // TODO: Pegar o ID do usuário logado
            
            // Verifica se já é favorito
            if (favoritoRepository.existsByUsuarioIdAndLivroId(usuarioId, livroId)) {
                return ResponseEntity.badRequest().body("Livro já está nos favoritos");
            }

            // Busca o livro e o usuário
            var livro = livroRepository.findById(livroId);
            var usuario = clienteRepository.findById(usuarioId);

            if (livro.isEmpty() || usuario.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            // Cria novo favorito
            Favorito favorito = new Favorito();
            favorito.setLivro(livro.get());
            favorito.setUsuario(usuario.get());
            
            favoritoRepository.save(favorito);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao adicionar favorito: " + e.getMessage());
        }
    }

    @DeleteMapping("/{livroId}")
    public ResponseEntity<?> removerFavorito(@PathVariable Long livroId) {
        try {
            Integer usuarioId = 1; // TODO: Pegar o ID do usuário logado
            
            var favorito = favoritoRepository.findByUsuarioIdAndLivroId(usuarioId, livroId);
            if (favorito.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            favoritoRepository.delete(favorito.get());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao remover favorito: " + e.getMessage());
        }
    }
}