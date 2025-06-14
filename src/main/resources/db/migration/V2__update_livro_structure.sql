-- Primeiro, remover as foreign keys existentes
ALTER TABLE emprestimo
DROP FOREIGN KEY fk_Usuario_has_Livro_Livro1;

-- Adicionar a coluna ID na tabela livro se não existir
ALTER TABLE livro
ADD COLUMN id BIGINT PRIMARY KEY AUTO_INCREMENT FIRST;

-- Recriar a foreign key usando o ID ao invés do ISBN
ALTER TABLE emprestimo
ADD CONSTRAINT fk_emprestimo_livro
FOREIGN KEY (Livro_id) REFERENCES livro(id);

-- Atualizar a estrutura da tabela livro
ALTER TABLE livro
MODIFY COLUMN ISBN VARCHAR(255) NOT NULL,
ADD UNIQUE INDEX uk_isbn (ISBN);
