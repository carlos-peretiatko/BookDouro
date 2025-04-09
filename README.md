<img width="100%" src="https://capsule-render.vercel.app/api?type=waving&height=100&color=gradient&section=header&animation=fadeIn&fontAlignY=45"/>

# Book D'ouro - Sistema de Biblioteca

## Descrição
O **Book D'ouro** é um sistema de gestão para uma livraria/biblioteca, desenvolvido para organizar o estoque de livros, permitir o cadastro de novos itens e gerenciar as transações de empréstimos. O sistema também oferece funcionalidades de busca, registro de usuários e visualização das obras disponíveis.

Este projeto foi desenvolvido como parte da disciplina de **Desenvolvimento de Software** e visa aplicar conceitos de programação, design de sistemas e boas práticas de desenvolvimento de software.

---

## Funcionalidades

- **Cadastro de livros:** Permite cadastrar novos livros com informações como título, autor, ano de publicação, gênero e quantidade disponível.
- **Cadastro de usuários:** Usuários podem se registrar para fazer empréstimos de livros.
- **Empréstimo de livros:** Sistema para realizar empréstimos de livros, com controle de datas de devolução e alertas para devoluções atrasadas.
- **Busca de livros:** Usuários podem buscar livros por título, autor ou gênero.
- **Visualização de livros disponíveis:** Exibe os livros disponíveis no estoque para os usuários.
- **Administração de livros:** Administradores podem visualizar, editar e excluir registros de livros.

---

## Tecnologias Utilizadas

### Frontend
- **HTML**
- **CSS3**
- **JavaScript**
- **Framework:** React

### Backend
- **Node.js**
- **Framework:** Express
- **Banco de dados:** MongoDB
- **Autenticação:** JWT

---

## Como Rodar o Projeto

1. **Clonar o Repositório**  
   Clone este repositório para sua máquina local:

   ```bash
   git clone https://github.com/seu-usuario/book-douro.git

3. Instalar Dependências
   Entre na pasta do projeto e instale as dependências:

   ```bash
   cd book-douro
   npm install

4. Configuração do Banco de Dados
   Certifique-se de ter o MongoDB instalado ou utilize uma instância de MongoDB Atlas.
   Crie um arquivo ```.env``` na raiz do projeto e adicione as variáveis de ambiente:
   
   ```bash
   MONGO_URI=mongodb://localhost:27017/bookdouro
   JWT_SECRET=seu-segredo-de-jwt

6. Rodar o Servidor
   Para rodar o servidor, use o seguinte comando:
   
     ```bash
     npm start
  O servidor estará rodando em http://localhost:5000.


6. Rodar o Frontend
   Para rodar a parte frontend, entre no diretório client e execute o comando:
   
     ```bash
     cd client
     npm start
  O frontend estará disponível em http://localhost:3000.

---

### Estrutura de Diretórios
     book-douro/
     ├── client/               # Diretório com o frontend (React)
     ├── server/               # Backend (Node.js)
     │   ├── controllers/      # Lógica de controle das rotas
     │   ├── models/           # Modelos de banco de dados
     │   ├── routes/           # Definições das rotas da API
     │   ├── utils/            # Utilitários como autenticação
     │   └── app.js            # Configuração do servidor
     ├── .env                  # Arquivo de variáveis de ambiente
     ├── package.json          # Dependências do projeto
     └── README.md             # Este arquivo
---

### Contribuição
1. Faça um fork do repositório.
2. Crie uma branch para a sua feature:
    ```bash
    git checkout -b feature/nome-da-feature
3. Faça commit das suas alterações:
    ```bash
    git commit -m 'Add new feature'
4. Envie para o repositório remoto:
   ```bash
   git push origin feature/nome-da-feature
5. Abra um pull request.

---

### Licença
Este projeto está licenciado sob a licença **MIT**. Veja o arquivo ```LICENSE``` para mais detalhes.

---

### Autores

- Carlos Eduardo Peretiatko
- André Nazário
- Vinicius Custódio

<img width="100%" src="https://capsule-render.vercel.app/api?type=waving&height=100&color=gradient&section=footer&animation=fadeIn&fontAlignY=65"/>
