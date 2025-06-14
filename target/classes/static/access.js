// Script para alternar a exibição das opções do filtro
document.addEventListener('DOMContentLoaded', () => {
    // Filter functionality
    const filterButton = document.querySelector('.filter-button');
    const filterContainer = document.querySelector('.filter-container');
    const filterOptions = document.querySelectorAll('.filter-options div');

    if (filterButton && filterContainer) {
        // Alternar a exibição das opções do filtro
        filterButton.addEventListener('click', () => {
            filterContainer.classList.toggle('active');
        });

        // Adicionar funcionalidade para cada opção do filtro
        filterOptions.forEach(option => {
            option.addEventListener('click', () => {
                alert(`Filtro aplicado: ${option.textContent}`);
                filterContainer.classList.remove('active');
            });
        });
    }

    // Accessibility functionality
    // Carregar preferências salvas
    loadSavedPreferences();

    // Configurar botões de tamanho de texto
    setupTextSizeButtons();

    // Configurar botões de contraste
    setupContrastButtons();

    // Configurar toggles de acessibilidade
    setupAccessibilityToggles();

    // Configurar atalhos de teclado
    setupKeyboardShortcuts();
});

function loadSavedPreferences() {
    try {
        // Carregar tamanho do texto
        const savedSize = localStorage.getItem('textSize') || 'medium';
        document.body.classList.add(`text-${savedSize}`);
        const activeButton = document.querySelector(`.size-button[data-size="${savedSize}"]`);
        if (activeButton) {
            activeButton.classList.add('active');
        }

        // Carregar contraste
        const savedContrast = localStorage.getItem('contrast') || 'normal';
        if (savedContrast === 'high') {
            document.body.classList.add('high-contrast');
            const contrastButton = document.querySelector('.contrast-button[data-contrast="high"]');
            if (contrastButton) {
                contrastButton.classList.add('active');
            }
        }

        // Carregar outras preferências
        const animations = localStorage.getItem('disableAnimations') === 'true';
        const textSpacing = localStorage.getItem('textSpacing') === 'true';
        const dyslexicFont = localStorage.getItem('dyslexicFont') === 'true';

        const animationsToggle = document.getElementById('animationsToggle');
        const textSpacingToggle = document.getElementById('textSpacingToggle');
        const dyslexicFontToggle = document.getElementById('dyslexicFontToggle');

        if (animationsToggle) {
            animationsToggle.checked = animations;
            if (animations) document.body.classList.add('no-animations');
        }

        if (textSpacingToggle) {
            textSpacingToggle.checked = textSpacing;
            if (textSpacing) document.body.classList.add('increased-spacing');
        }

        if (dyslexicFontToggle) {
            dyslexicFontToggle.checked = dyslexicFont;
            if (dyslexicFont) document.body.classList.add('dyslexic-font');
        }
    } catch (error) {
        console.error('Erro ao carregar preferências:', error);
    }
}

function setupTextSizeButtons() {
    const sizeButtons = document.querySelectorAll('.size-button');
    if (!sizeButtons.length) return;
    
    sizeButtons.forEach(button => {
        button.addEventListener('click', () => {
            // Remover classes de tamanho existentes
            document.body.classList.remove('text-small', 'text-medium', 'text-large');
            
            // Remover active de todos os botões
            sizeButtons.forEach(btn => btn.classList.remove('active'));
            
            // Adicionar nova classe e marcar botão como ativo
            const size = button.dataset.size;
            document.body.classList.add(`text-${size}`);
            button.classList.add('active');
            
            // Salvar preferência
            localStorage.setItem('textSize', size);
        });
    });
}

function setupContrastButtons() {
    const contrastButtons = document.querySelectorAll('.contrast-button');
    if (!contrastButtons.length) return;
    
    contrastButtons.forEach(button => {
        button.addEventListener('click', () => {
            contrastButtons.forEach(btn => btn.classList.remove('active'));
            const contrast = button.dataset.contrast;
            
            if (contrast === 'high') {
                document.body.classList.add('high-contrast');
                button.classList.add('active');
            } else {
                document.body.classList.remove('high-contrast');
                const normalButton = document.querySelector('.contrast-button[data-contrast="normal"]');
                if (normalButton) {
                    normalButton.classList.add('active');
                }
            }
            
            localStorage.setItem('contrast', contrast);
        });
    });
}

function setupAccessibilityToggles() {
    // Desativar animações
    const animationsToggle = document.getElementById('animationsToggle');
    if (animationsToggle) {
        animationsToggle.addEventListener('change', function() {
            document.body.classList.toggle('no-animations', this.checked);
            localStorage.setItem('disableAnimations', this.checked);
        });
    }

    // Aumentar espaçamento
    const textSpacingToggle = document.getElementById('textSpacingToggle');
    if (textSpacingToggle) {
        textSpacingToggle.addEventListener('change', function() {
            document.body.classList.toggle('increased-spacing', this.checked);
            localStorage.setItem('textSpacing', this.checked);
        });
    }

    // Fonte para dislexia
    const dyslexicFontToggle = document.getElementById('dyslexicFontToggle');
    if (dyslexicFontToggle) {
        dyslexicFontToggle.addEventListener('change', function() {
            document.body.classList.toggle('dyslexic-font', this.checked);
            localStorage.setItem('dyslexicFont', this.checked);
        });
    }
}

function setupKeyboardShortcuts() {
    document.addEventListener('keydown', function(e) {
        if (e.altKey) {
            switch(e.key) {
                case '1':
                    window.location.href = 'home.html';
                    break;
                case '2':
                    window.location.href = 'search.html';
                    break;
                case '3':
                    window.location.href = 'history.html';
                    break;
                case '4':
                    window.location.href = 'access.html';
                    break;
                case '5':
                    window.location.href = 'profile.html';
                    break;
            }
        }
    });
}