document.addEventListener('DOMContentLoaded', () => {
    // Função para carregar livros da API por categoria
    async function loadBooks(category) {
        try {
            console.log(`Carregando livros da categoria: ${category}`);
            const url = `http://localhost:8080/api/livros/genero/${category}`;
            console.log(`URL da requisição: ${url}`);
            
            const response = await fetch(url);
            console.log('Status da resposta:', response.status);
            
            if (!response.ok) {
                const errorText = await response.text();
                console.error('Erro detalhado:', errorText);
                throw new Error(`Erro ao carregar livros: ${response.status} - ${errorText}`);
            }
            
            const data = await response.json();
            console.log(`Livros carregados para ${category}:`, data);
            return data;
        } catch (error) {
            console.error('Erro ao carregar livros:', error);
            return [];
        }
    }

    // Função para criar seção de livros com dados da API
    async function createBookSection(category, booksData=null) {
        console.log(`Criando seção para categoria: ${category}`);
        const section = document.createElement('section');
        section.className = 'book-section';
        
        const books = booksData || await loadBooks(category);
        console.log(`Total de livros na categoria ${category}:`, books.length);
        
        section.innerHTML = `
            <h2>${category}:</h2>
            <div class="book-slider">
                <button class="slider-arrow left">‹</button>
                <div class="book-list">
                    ${books.map(book => {
                        console.log(`Renderizando livro:`, book);
                        return `
                            <div class="book-card" data-id="${book.id || ''}">
                                <div class="book-cover">
                                    <img src="${book.imagemUrl || 'https://via.placeholder.com/150x200?text=Sem+Capa'}" 
                                         alt="${book.titulo || 'Título não disponível'}" 
                                         title="${book.titulo || 'Título não disponível'}">
                                </div>
                                <div class="book-info">
                                    <h3>${book.titulo || 'Título não disponível'}</h3>
                                    <p class="author">${book.autor || 'Autor não disponível'}</p>
                                    <p class="year">${book.anoDePublicacao || 'Ano não disponível'}</p>
                                    <div class="book-status ${book.numeroDeExemplares > 0 ? 'available' : 'unavailable'}">
                                        <i class="fas ${book.numeroDeExemplares > 0 ? 'fa-check-circle' : 'fa-times-circle'}"></i>
                                        ${book.numeroDeExemplares > 0 ? 'Disponível' : 'Indisponível'}
                                    </div>
                                    <button class="btn-details" onclick="window.location.href='book-details.html?id=${book.id || ''}'">
                                        <i class="fas fa-info-circle"></i> Ver Detalhes
                                    </button>
                                </div>
                            </div>
                        `;
                    }).join('')}
                </div>
                <button class="slider-arrow right">›</button>
            </div>
        `;

        return section;
    }

    // Função para adicionar eventos aos cards
    function addCardClickEvents(context=document) {
        context.querySelectorAll('.book-card').forEach(card => {
            card.addEventListener('click', (e) => {
                // Previne a navegação se o clique foi no botão
                if (e.target.closest('.btn-details')) return;
                
                const bookId = card.dataset.id;
                window.location.href = `book-details.html?id=${bookId}`;
            });
        });
    }

    // Função para adicionar eventos ao slider
    function addSliderEvents(slider) {
        const bookList = slider.querySelector('.book-list');
        const leftArrow = slider.querySelector('.slider-arrow.left');
        const rightArrow = slider.querySelector('.slider-arrow.right');
        const cardWidth = 280; // Nova largura do card + margem

        leftArrow?.addEventListener('click', () => {
            bookList.scrollBy({
                left: -cardWidth,
                behavior: 'smooth'
            });
        });

        rightArrow?.addEventListener('click', () => {
            bookList.scrollBy({
                left: cardWidth,
                behavior: 'smooth'
            });
        });
    }

    // Inicializar a página
    async function initializePage() {
        const content = document.querySelector('.content');
        const categories = ['Romance', 'Ficção', 'Terror', 'Aventura', 'Educacionais'];

        const loadingMessage = document.createElement('div');
        loadingMessage.className = 'loading-message';
        loadingMessage.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Carregando livros...';
        content.appendChild(loadingMessage);

        try {
            for (const category of categories) {
                const section = await createBookSection(category);
                content.appendChild(section);
                addSliderEvents(section.querySelector('.book-slider'));
                addCardClickEvents(section);

                // Animar entrada da seção
                section.style.opacity = '0';
                section.style.transform = 'translateY(20px)';
                setTimeout(() => {
                    section.style.transition = 'all 0.5s ease';
                    section.style.opacity = '1';
                    section.style.transform = 'translateY(0)';
                }, 100);
            }
        } catch (error) {
            console.error('Erro ao carregar livros:', error);
            content.innerHTML = `
                <div class="error-message">
                    <i class="fas fa-exclamation-circle"></i>
                    <h2>Erro ao carregar os livros</h2>
                    <p>Por favor, tente novamente mais tarde.</p>
                </div>
            `;
        } finally {
            loadingMessage.remove();
        }
    }

    initializePage();
});

document.addEventListener('DOMContentLoaded', async () => {
    const bookCategories = document.getElementById('bookCategories');
    const loading = document.getElementById('loading');
    const error = document.getElementById('error');
    const quickSearch = document.getElementById('quickSearch');
    const searchButton = document.getElementById('searchButton');
    const retryButton = document.getElementById('retryButton');

    // Categorias de livros
    const categories = [
        'Fantasia',
        'Ficção Científica',
        'Romance',
        'Literatura Brasileira',
        'História'
    ];

    // Função para carregar todos os livros
    async function loadAllBooks() {
        try {
            const response = await fetch('http://localhost:8080/api/livros');
            if (!response.ok) throw new Error('Erro ao carregar livros');
            return await response.json();
        } catch (error) {
            console.error('Erro ao carregar livros:', error);
            throw error;
        }
    }

    // Função para agrupar livros por categoria
    function groupBooksByCategory(books) {
        return books.reduce((acc, book) => {
            const category = book.genero || 'Outros';
            if (!acc[category]) {
                acc[category] = [];
            }
            acc[category].push(book);
            return acc;
        }, {});
    }

    // Função para criar card de livro
    function createBookCard(book) {
        return `
            <div class="book-card" data-id="${book.id}">
                <div class="book-cover">
                    <img src="${book.imagemUrl || 'https://via.placeholder.com/150x200?text=Sem+Capa'}" 
                         alt="${book.titulo}" 
                         onerror="this.src='https://via.placeholder.com/150x200?text=Sem+Capa'">
                    ${book.numeroDeExemplares > 0 
                        ? '<span class="status available">Disponível</span>'
                        : '<span class="status unavailable">Indisponível</span>'}
                </div>
                <div class="book-info">
                    <h3>${book.titulo}</h3>
                    <p class="author"><i class="fas fa-user"></i> ${book.autor}</p>
                    <p class="year"><i class="fas fa-calendar"></i> ${book.anoDePublicacao}</p>
                    <button class="details-button" onclick="window.location.href='book-details.html?id=${book.id}'">
                        <i class="fas fa-info-circle"></i> Ver Detalhes
                    </button>
                </div>
            </div>
        `;
    }

    // Função para criar seção de categoria
    function createCategorySection(categoryName, books) {
        if (!books || books.length === 0) return '';
        
        return `
            <section class="category-section">
                <h2>${categoryName}</h2>
                <div class="book-slider">
                    <button class="slider-arrow left" aria-label="Anterior">
                        <i class="fas fa-chevron-left"></i>
                    </button>
                    <div class="book-list">
                        ${books.map(book => createBookCard(book)).join('')}
                    </div>
                    <button class="slider-arrow right" aria-label="Próximo">
                        <i class="fas fa-chevron-right"></i>
                    </button>
                </div>
            </section>
        `;
    }

    // Função para inicializar os sliders
    function initializeSliders() {
        document.querySelectorAll('.book-slider').forEach(slider => {
            const bookList = slider.querySelector('.book-list');
            const leftArrow = slider.querySelector('.slider-arrow.left');
            const rightArrow = slider.querySelector('.slider-arrow.right');
            const scrollAmount = 300;

            leftArrow.addEventListener('click', () => {
                bookList.scrollBy({ left: -scrollAmount, behavior: 'smooth' });
            });

            rightArrow.addEventListener('click', () => {
                bookList.scrollBy({ left: scrollAmount, behavior: 'smooth' });
            });
        });
    }

    // Função para carregar e exibir os livros
    async function loadAndDisplayBooks() {
        loading.style.display = 'flex';
        error.style.display = 'none';
        bookCategories.innerHTML = '';

        try {
            const allBooks = await loadAllBooks();
            const booksByCategory = groupBooksByCategory(allBooks);
            
            const categorySections = categories
                .filter(category => booksByCategory[category]?.length > 0)
                .map(category => createCategorySection(category, booksByCategory[category]))
                .join('');

            // Adicionar outros livros no final
            if (booksByCategory['Outros']?.length > 0) {
                categorySections += createCategorySection('Outros', booksByCategory['Outros']);
            }

            bookCategories.innerHTML = categorySections;
            initializeSliders();
            loading.style.display = 'none';
        } catch (err) {
            console.error('Erro:', err);
            loading.style.display = 'none';
            error.style.display = 'flex';
        }
    }

    // Event Listeners
    searchButton.addEventListener('click', () => {
        const searchTerm = quickSearch.value.trim();
        if (searchTerm) {
            window.location.href = `search.html?q=${encodeURIComponent(searchTerm)}`;
        }
    });

    quickSearch.addEventListener('keypress', (e) => {
        if (e.key === 'Enter') {
            searchButton.click();
        }
    });

    retryButton.addEventListener('click', loadAndDisplayBooks);

    // Inicializar a página
    loadAndDisplayBooks();
});