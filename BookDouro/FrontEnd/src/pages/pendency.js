document.addEventListener('DOMContentLoaded', () => {
    console.log("Página de Pendências carregada.");
    // Aqui você pode adicionar funcionalidades dinâmicas, como carregar dados do backend.
});

// Adicionar eventos de clique aos cards de livros
document.querySelectorAll('.book-card').forEach(card => {
    card.addEventListener('click', () => {
        // Aqui você pode pegar o ID real do livro de algum atributo data-*
        const bookId = '1'; // Exemplo
        window.location.href = `book-details.html?id=${bookId}`;
    });
});