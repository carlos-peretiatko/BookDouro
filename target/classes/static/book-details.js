document.addEventListener('DOMContentLoaded', function() {
    // Recuperar o ID do livro da URL
    const urlParams = new URLSearchParams(window.location.search);
    const bookId = urlParams.get('id');

    // Verificar o modo de alto contraste
    const isHighContrast = localStorage.getItem('highContrast') === 'true';
    if (isHighContrast) {
        document.body.classList.add('high-contrast');
    }

    if (!bookId) {
        showError('ID do livro não encontrado');
        return;
    }

    // Carregar detalhes do livro
    loadBookDetails(bookId);

    // Configurar eventos
    setupEvents();
});

async function loadBookDetails(bookId) {
    try {
        const response = await fetch(`http://localhost:8080/api/livros/${bookId}`);
        if (!response.ok) throw new Error('Livro não encontrado');
        
        const book = await response.json();
        updateBookDetails(book);
        loadBookReviews(bookId);
    } catch (error) {
        showError('Erro ao carregar detalhes do livro');
        console.error('Erro:', error);
    }
}

function updateBookDetails(book) {
    // Atualizar capa e informações básicas
    document.getElementById('bookCover').src = book.imagemUrl || 'https://via.placeholder.com/300x450.png?text=Sem+Capa';
    document.getElementById('bookTitle').textContent = book.titulo;
    document.getElementById('bookAuthor').textContent = book.autor;
    document.getElementById('bookYear').textContent = book.anoDePublicacao;
    document.getElementById('bookGenre').textContent = book.genero;
    document.getElementById('bookCopies').textContent = book.numeroDeExemplares;
    
    // Atualizar sinopse
    document.getElementById('bookSynopsis').textContent = book.sinopse || 'Sinopse não disponível.';
    
    // Atualizar detalhes adicionais
    document.getElementById('bookIsbn').textContent = book.isbn || 'N/A';
    document.getElementById('bookPublisher').textContent = book.editora || 'N/A';
    document.getElementById('bookPages').textContent = book.numeroDePaginas || 'N/A';
    document.getElementById('bookLanguage').textContent = book.idioma || 'Português';

    // Verificar se o livro está disponível
    const btnReservar = document.getElementById('btnReservar');
    if (book.numeroDeExemplares > 0) {
        btnReservar.disabled = false;
        btnReservar.innerHTML = '<i class="fas fa-bookmark"></i> Reservar';
    } else {
        btnReservar.disabled = true;
        btnReservar.innerHTML = '<i class="fas fa-exclamation-circle"></i> Indisponível';
    }

    // Verificar se o livro está favoritado
    checkIfFavorited(book.id);
}

async function loadBookReviews(bookId) {
    try {
        const response = await fetch(`http://localhost:8080/api/avaliacoes/livro/${bookId}`);
        if (!response.ok) throw new Error('Erro ao carregar avaliações');
        
        const reviews = await response.json();
        updateReviewsSection(reviews);
    } catch (error) {
        console.error('Erro ao carregar avaliações:', error);
        document.getElementById('reviewsList').innerHTML = '<p>Erro ao carregar avaliações</p>';
    }
}

function updateReviewsSection(reviews) {
    const reviewsList = document.getElementById('reviewsList');
    const averageRatingElement = document.getElementById('averageRating');
    const totalRatingsElement = document.getElementById('totalRatings');

    if (!reviews || reviews.length === 0) {
        reviewsList.innerHTML = '<p>Nenhuma avaliação ainda.</p>';
        averageRatingElement.textContent = '0.0';
        totalRatingsElement.textContent = '(0 avaliações)';
        updateStarRating(document.querySelector('.ratings-summary .stars'), 0);
        return;
    }

    // Calcular média das avaliações
    const totalRating = reviews.reduce((sum, review) => sum + review.rating, 0);
    const averageRating = totalRating / reviews.length;
    const formattedAverage = averageRating.toFixed(1);

    // Atualizar estatísticas
    averageRatingElement.textContent = formattedAverage;
    totalRatingsElement.textContent = `(${reviews.length} avaliação${reviews.length !== 1 ? 's' : ''})`;
    updateStarRating(document.querySelector('.ratings-summary .stars'), Math.round(averageRating));

    // Mostrar reviews
    reviewsList.innerHTML = reviews.map(review => createReviewHTML(review)).join('');
}

function createReviewHTML(review) {
    return `
        <div class="review">
            <div class="review-header">
                <div class="stars">${createStars(review.rating)}</div>
                <span class="review-date">${formatDate(review.data)}</span>
            </div>
            <p class="review-text">${review.comentario}</p>
        </div>
    `;
}

function createStars(rating) {
    return Array(5).fill('').map((_, index) => 
        `<i class="fa${index < rating ? 's' : 'r'} fa-star"></i>`
    ).join('');
}

function formatDate(dateString) {
    const date = new Date(dateString);
    return date.toLocaleDateString('pt-BR');
}

function updateStarRating(container, rating) {
    const stars = container.querySelectorAll('i');
    stars.forEach((star, index) => {
        if (index < rating) {
            star.className = 'fas fa-star';
        } else {
            star.className = 'far fa-star';
        }
    });
}

async function checkIfFavorited(bookId) {
    try {
        const response = await fetch(`http://localhost:8080/api/usuarios/favoritos/${bookId}`);
        const isFavorited = await response.json();
        
        const btnFavoritar = document.getElementById('btnFavoritar');
        if (isFavorited) {
            btnFavoritar.innerHTML = '<i class="fas fa-heart"></i> Favoritado';
            btnFavoritar.classList.add('favorited');
        }
    } catch (error) {
        console.error('Erro ao verificar favorito:', error);
    }
}

function setupEvents() {
    // Evento de reserva
    document.getElementById('btnReservar').addEventListener('click', async () => {
        const bookId = new URLSearchParams(window.location.search).get('id');
        try {
            const response = await fetch(`http://localhost:8080/api/emprestimos`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ livroId: bookId })
            });

            if (!response.ok) throw new Error('Erro ao reservar livro');

            alert('Livro reservado com sucesso!');
            window.location.reload();
        } catch (error) {
            alert('Erro ao reservar livro. Tente novamente.');
            console.error('Erro:', error);
        }
    });

    // Evento de favoritar
    document.getElementById('btnFavoritar').addEventListener('click', async () => {
        const bookId = new URLSearchParams(window.location.search).get('id');
        const btn = document.getElementById('btnFavoritar');
        const isFavorited = btn.classList.contains('favorited');

        try {
            const response = await fetch(`http://localhost:8080/api/usuarios/favoritos/${bookId}`, {
                method: isFavorited ? 'DELETE' : 'POST'
            });

            if (!response.ok) throw new Error('Erro ao atualizar favorito');

            btn.innerHTML = isFavorited 
                ? '<i class="far fa-heart"></i> Favoritar'
                : '<i class="fas fa-heart"></i> Favoritado';
            btn.classList.toggle('favorited');
        } catch (error) {
            alert('Erro ao atualizar favorito. Tente novamente.');
            console.error('Erro:', error);
        }
    });

    // Modal de avaliação
    const modal = document.getElementById('reviewModal');
    const btnAddReview = document.getElementById('btnAddReview');
    const closeModal = document.querySelector('.close-modal');
    
    btnAddReview.addEventListener('click', () => modal.style.display = 'block');
    closeModal.addEventListener('click', () => modal.style.display = 'none');
    
    // Fechar modal clicando fora
    window.addEventListener('click', (e) => {
        if (e.target == modal) modal.style.display = 'none';
    });

    // Estrelas da avaliação
    const ratingStars = document.querySelectorAll('.rating-input .stars i');
    let currentRating = 0;

    ratingStars.forEach(star => {
        star.addEventListener('mouseover', function() {
            const rating = parseInt(this.dataset.rating);
            updateStarRating(this.parentElement, rating);
        });

        star.addEventListener('click', function() {
            currentRating = parseInt(this.dataset.rating);
            updateStarRating(this.parentElement, currentRating);
        });

        star.addEventListener('mouseout', function() {
            updateStarRating(this.parentElement, currentRating);
        });
    });

    // Enviar avaliação
    document.getElementById('submitReview').addEventListener('click', async () => {
        const bookId = new URLSearchParams(window.location.search).get('id');
        const comment = document.getElementById('reviewText').value;

        if (!currentRating) {
            alert('Por favor, selecione uma avaliação');
            return;
        }

        try {
            const userId = localStorage.getItem('userId') || 1; // Get logged in user ID or fallback to 1
            const response = await fetch(`http://localhost:8080/api/avaliacoes/livro/${bookId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    rating: currentRating,
                    comentario: comment,
                    usuarioId: parseInt(userId)
                })
            });

            if (!response.ok) {
                const errorText = await response.text();
                throw new Error(errorText || 'Erro ao enviar avaliação');
            }

            modal.style.display = 'none';
            document.getElementById('reviewText').value = '';
            currentRating = 0;
            updateStarRating(document.querySelector('.rating-input .stars'), 0);
            loadBookReviews(bookId);
        } catch (error) {
            alert('Erro ao enviar avaliação. Tente novamente.');
            console.error('Erro:', error);
        }
    });
}

function showError(message) {
    const content = document.querySelector('.content');
    content.innerHTML = `
        <div class="error-message">
            <i class="fas fa-exclamation-circle"></i>
            <h2>${message}</h2>
            <a href="home.html" class="btn-back">
                <i class="fas fa-home"></i> Voltar para Página Inicial
            </a>
        </div>
    `;
}