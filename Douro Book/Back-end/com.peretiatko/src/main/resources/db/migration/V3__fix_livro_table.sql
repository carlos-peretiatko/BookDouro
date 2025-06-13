-- Desabilitar verificação de chaves estrangeiras temporariamente
SET FOREIGN_KEY_CHECKS=0;

-- Criar uma tabela temporária com a estrutura correta
CREATE TABLE livro_temp (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ISBN VARCHAR(255) NOT NULL UNIQUE,
    Titulo VARCHAR(255),
    Autor VARCHAR(255),
    Genero VARCHAR(255),
    Editora VARCHAR(255),
    Numero_de_exemplares INT,
    Ano_de_publicacao INT,
    Cover_URL VARCHAR(255),
    Sinopse TEXT,
    Numero_de_paginas INT,
    Idioma VARCHAR(255)
);

-- Copiar dados da tabela antiga para a nova
INSERT INTO livro_temp (ISBN, Titulo, Autor, Genero, Editora, Numero_de_exemplares, 
    Ano_de_publicacao, Cover_URL, Sinopse, Numero_de_paginas, Idioma)
SELECT ISBN, Titulo, Autor, Genero, Editora, Numero_de_exemplares, 
    Ano_de_publicacao, Cover_URL, Sinopse, Numero_de_paginas, Idioma
FROM livro;

-- Remover a tabela antiga
DROP TABLE livro;

-- Renomear a tabela temporária
RENAME TABLE livro_temp TO livro;

-- Atualizar a referência na tabela emprestimo
ALTER TABLE emprestimo
ADD COLUMN Livro_id BIGINT,
ADD CONSTRAINT fk_emprestimo_livro FOREIGN KEY (Livro_id) REFERENCES livro(id);

-- Habilitar verificação de chaves estrangeiras novamente
SET FOREIGN_KEY_CHECKS=1;
