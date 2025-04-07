document.addEventListener('DOMContentLoaded', () => {
    const searchForm = document.getElementById('searchForm');
    const bookList = document.querySelector('.book-list');

    searchForm.addEventListener('submit', (event) => {
        event.preventDefault();

        // Simulação de resultados de pesquisa
        const results = [
            { title: 'Dom Casmurro', author: 'Machado de Assis', cover: 'https://via.placeholder.com/150' },
            { title: 'O Cortiço', author: 'Aluísio Azevedo', cover: 'https://via.placeholder.com/150' },
            { title: 'Memórias Póstumas de Brás Cubas', author: 'Machado de Assis', cover: 'https://via.placeholder.com/150' },
        ];

        // Limpar resultados anteriores
        bookList.innerHTML = '';

        // Exibir resultados
        results.forEach(book => {
            const bookCard = document.createElement('div');
            bookCard.classList.add('book-card');
            bookCard.innerHTML = `
                <img src="${book.cover}" alt="${book.title}">
                <div class="book-info">
                    <h3>${book.title}</h3>
                    <p>${book.author}</p>
                </div>
            `;
            bookList.appendChild(bookCard);
        });
    });
});