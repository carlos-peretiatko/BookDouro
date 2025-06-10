document.addEventListener('DOMContentLoaded', () => {
    const bookISBN = new URLSearchParams(window.location.search).get('isbn');

    // Buscar detalhes do livro pelo ISBN
    fetch(`http://localhost:8080/api/livros/${bookISBN}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('bookCover').src = data.coverUrl;
            document.getElementById('bookTitle').textContent = data.title;
            document.getElementById('bookAuthor').textContent = data.author;
            document.getElementById('bookGenre').textContent = data.genre;
            document.getElementById('bookYear').textContent = data.year;
            document.getElementById('bookISBN').textContent = data.isbn;
            document.getElementById('bookPublisher').textContent = data.publisher;
            document.getElementById('bookAvailability').textContent = data.availability ? 'Disponível' : 'Indisponível';
            document.getElementById('bookDescription').textContent = data.description;
        })
        .catch(error => console.error('Erro ao buscar detalhes do livro:', error));
});

// Registrar empréstimo
document.querySelector('.borrow-button').addEventListener('click', () => {
    const bookISBN = document.getElementById('bookISBN').textContent;

    fetch('http://localhost:8080/api/emprestimos', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            usuarioId: 1, // Substituir pelo ID do usuário logado
            livroISBN: bookISBN,
            dataInicio: new Date().toISOString().split('T')[0],
            dataDevolucao: new Date(Date.now() + 7 * 24 * 60 * 60 * 1000).toISOString().split('T')[0]
        })
    })
        .then(response => {
            if (response.ok) {
                alert('Empréstimo registrado com sucesso!');
            } else {
                alert('Erro ao registrar empréstimo.');
            }
        })
        .catch(error => console.error('Erro ao registrar empréstimo:', error));
});

document.getElementById('submitReview').addEventListener('click', () => {
    const reviewText = document.getElementById('reviewText').value;
    const bookISBN = document.getElementById('bookISBN').textContent;

    fetch(`http://localhost:8080/api/livros/${bookISBN}/reviews`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ usuarioId: 1, review: reviewText }) // Substituir pelo ID do usuário logado
    })
        .then(response => {
            if (response.ok) {
                alert('Avaliação enviada com sucesso!');
            } else {
                alert('Erro ao enviar avaliação.');
            }
        })
        .catch(error => console.error('Erro ao enviar avaliação:', error));
});