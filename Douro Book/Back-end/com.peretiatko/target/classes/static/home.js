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
    const mainContent = document.querySelector('main');
    
    // Adiciona seção de boas-vindas com animação
    const welcomeSection = document.querySelector('.welcome-section');
    if (welcomeSection) {
        welcomeSection.style.opacity = '0';
        welcomeSection.style.transform = 'translateY(20px)';
        setTimeout(() => {
            welcomeSection.style.transition = 'all 0.5s ease';
            welcomeSection.style.opacity = '1';
            welcomeSection.style.transform = 'translateY(0)';
        }, 100);
    }

    // Função para verificar se uma URL de imagem é válida
    async function isImageValid(url) {
        try {
            const response = await fetch(url, { method: 'HEAD' });
            return response.ok;
        } catch (error) {
            return false;
        }
    }

    // Função para obter uma imagem de fallback baseada no gênero
    function getFallbackImage(genre) {
        const fallbacks = {
            'Ficção': 'https://img.freepik.com/free-vector/hand-drawn-flat-design-stack-books_23-2149334862.jpg',
            'Romance': 'https://img.freepik.com/free-vector/vintage-books-stacked-pile-hand-drawn-sketch-vector_460848-14851.jpg',
            'Terror': 'https://img.freepik.com/free-vector/halloween-concept-with-spell-book_23-2148652325.jpg',
            'Técnico': 'https://img.freepik.com/free-vector/studying-concept-illustration_114360-1301.jpg'
        };
        return fallbacks[genre] || 'https://img.freepik.com/free-vector/hand-drawn-flat-design-stack-books_23-2149334862.jpg';
    }

    // Função para carregar destaques da API
    async function loadDestaques() {
        try {
            const response = await fetch('http://localhost:8080/api/livros/destaques');
            if (!response.ok) throw new Error('Erro ao carregar destaques');
            const data = await response.json();
            console.log('Destaques carregados:', data);
            return data;
        } catch (error) {
            console.error('Erro:', error);
            return {};
        }
    }

    // Função para criar seção de livros com verificação de imagem
    async function createBookSection(category, books) {
        const section = document.createElement('section');
        section.className = 'book-section';
        
        // Adiciona efeito de fade in
        section.style.opacity = '0';
        section.style.transform = 'translateY(20px)';
        
        const booksHTML = await Promise.all(books.map(async (book) => {
            const imageUrl = book.coverUrl;
            const isValid = await isImageValid(imageUrl);
            const finalImageUrl = isValid ? imageUrl : getFallbackImage(category);
            
            return `
                <div class="book-card" data-id="${book.isbn}">
                    <img src="${finalImageUrl}" 
                         alt="${book.titulo}" 
                         title="${book.titulo}">
                    <div class="book-info">
                        <h3>${book.titulo}</h3>
                        <p>${book.autor}</p>
                        <p class="book-year">${book.anoDePublicacao}</p>
                    </div>
                </div>
            `;
        }));

        section.innerHTML = `
            <h2>${category}</h2>
            <div class="book-slider">
                <button class="slider-arrow left" aria-label="Previous books">
                    <i class="fas fa-chevron-left"></i>
                </button>
                <div class="book-list">
                    ${booksHTML.join('')}
                </div>
                <button class="slider-arrow right" aria-label="Next books">
                    <i class="fas fa-chevron-right"></i>
                </button>
            </div>
        `;

        // Adiciona funcionalidade de slider
        const bookList = section.querySelector('.book-list');
        const leftButton = section.querySelector('.slider-arrow.left');
        const rightButton = section.querySelector('.slider-arrow.right');

        leftButton.addEventListener('click', () => {
            bookList.scrollBy({ left: -300, behavior: 'smooth' });
        });

        rightButton.addEventListener('click', () => {
            bookList.scrollBy({ left: 300, behavior: 'smooth' });
        });

        // Anima a seção aparecendo
        setTimeout(() => {
            section.style.transition = 'all 0.5s ease';
            section.style.opacity = '1';
            section.style.transform = 'translateY(0)';
        }, 100);

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

    // Carregar e exibir os destaques
    try {
        const destaques = await loadDestaques();
        
        // Limpar o conteúdo principal mantendo a seção de boas-vindas
        const welcomeSection = mainContent.querySelector('.welcome-section');
        mainContent.innerHTML = '';
        if (welcomeSection) mainContent.appendChild(welcomeSection);

        // Criar seções para cada categoria de destaques
        for (const [category, books] of Object.entries(destaques)) {
            if (books && books.length > 0) {
                const section = await createBookSection(category, books);
                mainContent.appendChild(section);
            }
        }

        // Adicionar eventos aos cards
        addCardClickEvents();

    } catch (error) {
        console.error('Erro ao carregar destaques:', error);
        mainContent.innerHTML += `
            <div class="error">
                <i class="fas fa-exclamation-circle"></i>
                <p>Erro ao carregar livros. Por favor, tente novamente mais tarde.</p>
            </div>
        `;
    }
});