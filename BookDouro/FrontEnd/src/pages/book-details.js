document.addEventListener('DOMContentLoaded', () => {
    // Pegar o ID do livro da URL
    const urlParams = new URLSearchParams(window.location.search);
    const bookId = urlParams.get('id');

    // Simular dados do livro (substitua por uma chamada à API real)
    const bookData = {
        id: bookId,
        title: 'Call of Cthulhu',
        author: 'H. P. Lovecraft',
        genre: 'Horror',
        year: '1920',
        isbn: '9780345534521',
        publisher: 'Arkham House',
        availability: 'Disponível',
        description: 'Call of Cthulhu é um conto de horror do autor americano H. P. Lovecraft, escrito em 1926 e publicado pela primeira vez na revista Weird Tales em 1928.',
        coverUrl: 'https://www.hu3br.com/wp-content/uploads/2018/11/call-of-chtulhu-analise-6.jpg'
    };

    // Preencher os dados na página
    document.getElementById('bookCover').src = bookData.coverUrl;
    document.getElementById('bookTitle').textContent = bookData.title;
    document.getElementById('bookAuthor').textContent = bookData.author;
    document.getElementById('bookGenre').textContent = bookData.genre;
    document.getElementById('bookYear').textContent = bookData.year;
    document.getElementById('bookISBN').textContent = bookData.isbn;
    document.getElementById('bookPublisher').textContent = bookData.publisher;
    document.getElementById('bookAvailability').textContent = bookData.availability;
    document.getElementById('bookDescription').textContent = bookData.description;

    // Adicionar evento ao botão de empréstimo
    const borrowButton = document.querySelector('.borrow-button');
    borrowButton.addEventListener('click', () => {
        alert('Funcionalidade de empréstimo será implementada em breve!');
    });
});