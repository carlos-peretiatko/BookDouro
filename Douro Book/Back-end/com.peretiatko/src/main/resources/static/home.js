document.addEventListener('DOMContentLoaded', () => {
    // Função para carregar livros da API por categoria
    async function loadBooks(category) {
        try {
            const response = await fetch(`http://localhost:8080/api/livros/categoria/${category}`);
            if (!response.ok) throw new Error('Erro ao carregar livros');
            return await response.json();
        } catch (error) {
            console.error('Erro:', error);
            return [];
        }
    }

    // Função para criar seção de livros com dados da API
    async function createBookSection(category) {
        const section = document.createElement('section');
        section.className = 'book-section';
        
        const books = await loadBooks(category);
        
        section.innerHTML = `
            <h2>${category}:</h2>
            <div class="book-slider">
                <button class="slider-arrow left">‹</button>
                <div class="book-list">
                    ${books.map(book => `
                        <div class="book-card" data-id="${book.isbn}">
                            <img src="${book.coverUrl}" alt="${book.titulo}">
                        </div>
                    `).join('')}
                </div>
                <button class="slider-arrow right">›</button>
            </div>
        `;

        return section;
    }

    // Função para adicionar eventos aos cards
    function addCardClickEvents(context=document) {
        context.querySelectorAll('.book-card').forEach(card => {
            card.style.cursor = 'pointer';
            card.addEventListener('click', () => {
                const bookId = card.dataset.id;
                window.location.href = `book-details.html?isbn=${bookId}`;
            });
        });
    }

    // Função para adicionar eventos ao slider
    function addSliderEvents(slider) {
        const bookList = slider.querySelector('.book-list');
        const leftArrow = slider.querySelector('.slider-arrow.left');
        const rightArrow = slider.querySelector('.slider-arrow.right');

        leftArrow?.addEventListener('click', () => {
            bookList.scrollBy({
                left: -300,
                behavior: 'smooth'
            });
        });

        rightArrow?.addEventListener('click', () => {
            bookList.scrollBy({
                left: 300,
                behavior: 'smooth'
            });
        });
    }

    // Inicializar a página
    async function initializePage() {
        const content = document.querySelector('.content');
        const categories = ['Romance', 'Ficção', 'Terror', 'Aventura', 'Educacionais'];

        for (const category of categories) {
            const section = await createBookSection(category);
            content.appendChild(section);
            addSliderEvents(section.querySelector('.book-slider'));
            addCardClickEvents(section);
        }
    }

    initializePage();
});

document.addEventListener('DOMContentLoaded', () => {
    fetch('/api/livros')
        .then(response => response.json())
        .then(livros => {
            const container = document.getElementById('livros-container');
            container.innerHTML = '';
            livros.forEach(livro => {
                const card = document.createElement('div');
                card.className = 'book-card';
                card.innerHTML = `
                    <h3>${livro.titulo}</h3>
                    <p><strong>Autor:</strong> ${livro.autor}</p>
                    <p><strong>Gênero:</strong> ${livro.genero}</p>
                    <p><strong>Editora:</strong> ${livro.editora}</p>
                    <p><strong>Ano:</strong> ${livro.ano_de_publicacao}</p>
                `;
                container.appendChild(card);
            });
        });
});