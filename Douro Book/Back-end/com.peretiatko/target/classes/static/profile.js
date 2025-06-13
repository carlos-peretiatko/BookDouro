document.addEventListener('DOMContentLoaded', async () => {
    const profileInfo = document.querySelector('.profile-info');
    const favoritesList = document.querySelector('#favorites-list');
    const borrowedList = document.querySelector('#borrowed-list');

    // Função para carregar dados do perfil
    async function loadProfileData() {
        try {
            const cpf = localStorage.getItem('userCpf'); // Assumindo que o CPF está armazenado no localStorage
            const response = await fetch(`/api/clientes/${cpf}`);
            if (!response.ok) throw new Error('Erro ao carregar dados do perfil');
            
            const userData = await response.json();
            updateProfileUI(userData);
        } catch (error) {
            console.error('Erro:', error);
        }
    }

    // Função para atualizar a UI com os dados do usuário
    function updateProfileUI(userData) {
        document.querySelector('.profile-info h2').textContent = userData.nome;
        document.querySelector('.hidden-content[data-type="email"]').textContent = userData.email;
        document.querySelector('.hidden-content[data-type="telefone"]').textContent = userData.telefone;
        document.querySelector('.profile-bio').textContent = userData.bio || 'Nenhuma biografia disponível.';
    }

    // Função para carregar livros favoritos
    async function loadFavoriteBooks() {
        try {
            const cpf = localStorage.getItem('userCpf');
            const response = await fetch(`/api/clientes/${cpf}/favoritos`);
            if (!response.ok) throw new Error('Erro ao carregar favoritos');
            
            const favorites = await response.json();
            updateBookList(favorites, favoritesList);
        } catch (error) {
            console.error('Erro:', error);
        }
    }

    // Função para carregar livros emprestados
    async function loadBorrowedBooks() {
        try {
            const cpf = localStorage.getItem('userCpf');
            const response = await fetch(`/api/clientes/${cpf}/emprestimos`);
            if (!response.ok) throw new Error('Erro ao carregar empréstimos');
            
            const borrowed = await response.json();
            updateBookList(borrowed, borrowedList);
        } catch (error) {
            console.error('Erro:', error);
        }
    }

    // Função para atualizar a lista de livros na UI
    function updateBookList(books, container) {
        container.innerHTML = books.map(book => `
            <div class="book-card" data-id="${book.isbn}">
                <img src="${book.coverUrl}" alt="${book.titulo}">
                <div class="book-info">
                    <h3>${book.titulo}</h3>
                    <p>${book.autor}</p>
                </div>
            </div>
        `).join('');

        // Adicionar eventos de clique
        container.querySelectorAll('.book-card').forEach(card => {
            card.addEventListener('click', () => {
                const bookId = card.dataset.id;
                window.location.href = `book-details.html?isbn=${bookId}`;
            });
        });
    }

    // Inicializar página
    loadProfileData();
    loadFavoriteBooks();
    loadBorrowedBooks();
});