CREATE TABLE IF NOT EXISTS livro (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    ISBN VARCHAR(255) UNIQUE,
    Titulo VARCHAR(255),
    Autor VARCHAR(255),
    Genero VARCHAR(255),
    Editora VARCHAR(255),
    Numero_de_exemplares INT,
    Ano_de_publicacao INT,
    Cover_URL VARCHAR(255),
    Sinopse TEXT,
    Numero_de_paginas INT,
    Idioma VARCHAR(50)
);
