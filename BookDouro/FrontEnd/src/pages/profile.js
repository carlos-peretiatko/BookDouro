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