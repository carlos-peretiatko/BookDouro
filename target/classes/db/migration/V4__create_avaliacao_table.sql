CREATE TABLE avaliacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    livro_id INT NOT NULL,
    usuario_id INT NOT NULL,
    rating INT NOT NULL,
    comentario TEXT,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (livro_id) REFERENCES livro(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);