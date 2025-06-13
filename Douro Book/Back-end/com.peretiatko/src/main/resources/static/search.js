document.addEventListener('DOMContentLoaded', () => {
    const searchInput = document.getElementById('searchInput');
    const searchType = document.getElementById('searchType');
    const searchButton = document.getElementById('searchButton');
    const bookList = document.querySelector('.search-results');

    async function fetchBooks() {
        const query = searchInput.value.trim();
        const type = searchType.value;
        
        if (!query) {
            bookList.innerHTML = '<div class="message-box info">Digite um termo para pesquisar.</div>';
            return;
        }

        bookList.innerHTML = '<div class="message-box loading">Buscando livros...</div>';
        
        try {
            const response = await fetch(`http://localhost:8080/api/livros/search?tipo=${type}&valor=${encodeURIComponent(query)}`);
            const data = await response.json();

            if (!response.ok) {
                throw new Error(data.message || 'Erro ao buscar livros');
            }

            bookList.innerHTML = '';
            
            if (!data.length) {
                bookList.innerHTML = '<div class="message-box info">Nenhum livro encontrado com estes critérios.</div>';
                return;
            }

            data.forEach(book => {
                const bookCard = document.createElement('div');
                bookCard.classList.add('book-card');
                bookCard.innerHTML = `
                    <img src="${book.imagemUrl || 'https://via.placeholder.com/150'}" 
                         alt="${book.titulo}" 
                         onerror="this.src='https://via.placeholder.com/150?text=Sem+Imagem'">
                    <div class="book-info">
                        <h3>${book.titulo}</h3>
                        <p class="author"><i class="fas fa-user"></i> ${book.autor}</p>
                        <p class="genre"><i class="fas fa-bookmark"></i> ${book.genero}</p>
                        <p class="year"><i class="fas fa-calendar"></i> ${book.anoDePublicacao}</p>
                        ${book.numeroDeExemplares > 0 
                            ? `<span class="available"><i class="fas fa-check"></i> Disponível</span>`
                            : `<span class="unavailable"><i class="fas fa-times"></i> Indisponível</span>`}
                    </div>
                `;
                bookCard.addEventListener('click', () => {
                    window.location.href = `book-details.html?id=${book.id}`;
                });
                bookList.appendChild(bookCard);
            });
        } catch (err) {
            bookList.innerHTML = `<div class="message-box error">
                <i class="fas fa-exclamation-circle"></i>
                ${err.message || 'Erro ao buscar livros. Por favor, tente novamente.'}
            </div>`;
            console.error('Erro na busca:', err);
        }
    }

    searchButton.addEventListener('click', (event) => {
        event.preventDefault();
        fetchBooks();
    });

    searchInput.addEventListener('keydown', (event) => {
        if (event.key === 'Enter') {
            event.preventDefault();
            fetchBooks();
        }
    });
});