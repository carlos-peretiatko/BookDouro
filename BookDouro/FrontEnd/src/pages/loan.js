//fazer ainda o metodo para achar os livros cadastrados

// Adicionar eventos de clique aos cards de livros
document.querySelectorAll('.book-card').forEach(card => {
    card.addEventListener('click', () => {
        // Aqui você pode pegar o ID real do livro de algum atributo data-*
        const bookId = '1'; // Exemplo
        window.location.href = `book-details.html?id=${bookId}`;
    });
});