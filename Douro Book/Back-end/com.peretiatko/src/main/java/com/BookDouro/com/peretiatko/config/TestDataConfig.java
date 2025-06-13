package com.BookDouro.com.peretiatko.config;

import com.BookDouro.com.peretiatko.model.Livro;
import com.BookDouro.com.peretiatko.repository.LivroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class TestDataConfig {

    @Autowired
    private LivroRepo livroRepo;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            if (livroRepo.count() == 0) {
                Livro livro1 = new Livro();
                livro1.setTitulo("O Hobbit");
                livro1.setAutor("J.R.R. Tolkien");
                livro1.setGenero("Fantasia");
                livro1.setIsbn("9788578277697");
                livro1.setEditora("HarperCollins");
                livro1.setNumeroDeExemplares(5);
                livro1.setAnoDePublicacao(1937);
                livro1.setImagemUrl("https://m.media-amazon.com/images/I/91M9xPIf10L._AC_UF1000,1000_QL80_.jpg");
                livroRepo.save(livro1);

                Livro livro2 = new Livro();
                livro2.setTitulo("1984");
                livro2.setAutor("George Orwell");
                livro2.setGenero("Ficção Distópica");
                livro2.setIsbn("9788535914849");
                livro2.setEditora("Companhia das Letras");
                livro2.setNumeroDeExemplares(3);
                livro2.setAnoDePublicacao(1949);
                livro2.setImagemUrl("https://m.media-amazon.com/images/I/81StSOpmkjL._AC_UF1000,1000_QL80_.jpg");
                livroRepo.save(livro2);
            }
        };
    }
}
