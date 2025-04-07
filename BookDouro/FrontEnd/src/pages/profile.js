document.addEventListener('DOMContentLoaded', () => {
    const eyeIcons = document.querySelectorAll('.icon-eye');

    eyeIcons.forEach(icon => {
        icon.addEventListener('click', () => {
            const parent = icon.parentElement;
            const isHidden = parent.classList.contains('hidden');

            if (isHidden) {
                // Mostrar o conteúdo
                const originalText = icon.dataset.originalText;
                parent.querySelector('.hidden-content').textContent = originalText;
                parent.classList.remove('hidden');
                icon.textContent = '👁️'; // Ícone de olho aberto
            } else {
                // Esconder o conteúdo
                const contentElement = parent.querySelector('.hidden-content');
                icon.dataset.originalText = contentElement.textContent;
                contentElement.textContent = '********';
                parent.classList.add('hidden');
                icon.textContent = '🙈'; // Ícone d
                // e olho fechado
            }
        });
    });
});

document.addEventListener('DOMContentLoaded', () => {
    const sliders = document.querySelectorAll('.book-slider');

    sliders.forEach(slider => {
        const bookList = slider.querySelector('.book-list');
        const leftArrow = slider.querySelector('.slider-arrow.left');
        const rightArrow = slider.querySelector('.slider-arrow.right');

        leftArrow.addEventListener('click', () => {
            bookList.scrollBy({
                left: -300, // Scroll 300px to the left
                behavior: 'smooth'
            });
        });

        rightArrow.addEventListener('click', () => {
            bookList.scrollBy({
                left: 300, // Scroll 300px to the right
                behavior: 'smooth'
            });
        });
    });
});