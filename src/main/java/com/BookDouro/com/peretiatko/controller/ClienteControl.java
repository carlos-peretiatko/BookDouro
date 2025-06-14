package com.BookDouro.com.peretiatko.controller;

import com.BookDouro.com.peretiatko.model.Cliente;
import com.BookDouro.com.peretiatko.repository.ClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ClienteControl {

    @Autowired
    private ClienteRepo clienteRepository;

    @GetMapping
    public ResponseEntity<?> listarClientes() {
        return ResponseEntity.ok(clienteRepository.findAll());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<?> buscarClientePorCpf(@PathVariable String cpf) {
        return clienteRepository.findByCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente) {
        try {
            System.out.println("Recebido cliente para cadastro: " + cliente);
            
            // Verificar campos obrigatórios
            if (cliente.getCpf() == null || cliente.getCpf().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("CPF é obrigatório");
            }
            if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Nome é obrigatório");
            }
            if (cliente.getEmail() == null || cliente.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Email é obrigatório");
            }
            if (cliente.getSenha() == null || cliente.getSenha().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Senha é obrigatória");
            }
            
            // Verificar se CPF já existe
            if (clienteRepository.existsByCpf(cliente.getCpf())) {
                return ResponseEntity.badRequest().body("CPF já cadastrado");
            }
            
            // Verificar se email já existe
            if (clienteRepository.existsByEmail(cliente.getEmail())) {
                return ResponseEntity.badRequest().body("Email já cadastrado");
            }

            Cliente novoCliente = clienteRepository.save(cliente);
            return ResponseEntity.ok(novoCliente);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao cadastrar: " + e.getMessage());
        }
    }

    @GetMapping("/check/{email}")
    public ResponseEntity<?> verificarUsuario(@PathVariable String email) {
        var cliente = clienteRepository.findByEmail(email);
        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of(
            "exists", true,
            "nome", cliente.get().getNome(),
            "senhaLength", cliente.get().getSenha().length()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("=== TENTATIVA DE LOGIN ===");
            System.out.println("Email recebido: [" + loginRequest.getEmail() + "]");
            
            if (loginRequest.getEmail() == null || loginRequest.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Email é obrigatório");
            }
            if (loginRequest.getSenha() == null || loginRequest.getSenha().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Senha é obrigatória");
            }

            var cliente = clienteRepository.findByEmail(loginRequest.getEmail());
            if (cliente.isEmpty()) {
                System.out.println("❌ Cliente não encontrado com o email: " + loginRequest.getEmail());
                return ResponseEntity.status(401).body("Email ou senha inválidos");
            }

            Cliente clienteEncontrado = cliente.get();
            System.out.println("✓ Cliente encontrado: " + clienteEncontrado.getNome());
            
            if (!clienteEncontrado.getSenha().equals(loginRequest.getSenha())) {
                System.out.println("❌ Senha incorreta para o email: " + loginRequest.getEmail());
                return ResponseEntity.status(401).body("Email ou senha inválidos");
            }
            
            System.out.println("✓ Login bem-sucedido para: " + loginRequest.getEmail());
            return ResponseEntity.ok(clienteEncontrado);
        } catch (Exception e) {
            System.out.println("❌ Erro durante o login: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro no servidor: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarCliente(@PathVariable Integer id, @Valid @RequestBody Cliente clienteAtualizado) {
        return clienteRepository.findById(id)
            .map(clienteExistente -> {
                // Atualizar apenas os campos que podem ser modificados
                clienteExistente.setNome(clienteAtualizado.getNome());
                clienteExistente.setEmail(clienteAtualizado.getEmail());
                clienteExistente.setTelefone(clienteAtualizado.getTelefone());
                
                // Manter os campos que não devem ser alterados
                clienteAtualizado.setSenha(clienteExistente.getSenha());
                clienteAtualizado.setFuncao(clienteExistente.getFuncao());
                clienteAtualizado.setCpf(clienteExistente.getCpf());
                
                try {
                    return ResponseEntity.ok(clienteRepository.save(clienteExistente));
                } catch (Exception e) {
                    return ResponseEntity.badRequest()
                        .body("Erro ao atualizar: " + e.getMessage());
                }
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirCliente(@PathVariable Integer id) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCliente(@PathVariable Integer id) {
        return clienteRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/change-password")
    public ResponseEntity<?> alterarSenha(@PathVariable Integer id, @RequestBody Map<String, String> senhas) {
        try {
            return clienteRepository.findById(id)
                .map(cliente -> {
                    String senhaAtual = senhas.get("senhaAtual");
                    String novaSenha = senhas.get("novaSenha");
                    
                    if (senhaAtual == null || novaSenha == null) {
                        return ResponseEntity.badRequest().body("Senha atual e nova senha são obrigatórias");
                    }
                    
                    if (!cliente.getSenha().equals(senhaAtual)) {
                        return ResponseEntity.status(401).body("Senha atual incorreta");
                    }
                    
                    cliente.setSenha(novaSenha);
                    clienteRepository.save(cliente);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao alterar senha: " + e.getMessage());
        }
    }
}

class LoginRequest {
    private String email;
    private String senha;

    // Construtor padrão necessário para deserialização
    public LoginRequest() {}

    public LoginRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}