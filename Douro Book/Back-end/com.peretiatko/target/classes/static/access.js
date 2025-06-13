// Script para alternar a exibição das opções do filtro
document.addEventListener('DOMContentLoaded', () => {
    const filterButton = document.querySelector('.filter-button');
    const filterContainer = document.querySelector('.filter-container');
    const filterOptions = document.querySelectorAll('.filter-options div');

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
});

document.addEventListener('DOMContentLoaded', function() {
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
    // Carregar tamanho do texto
    const savedSize = localStorage.getItem('textSize') || 'medium';
    document.body.classList.add(`text-${savedSize}`);
    document.querySelector(`.size-button[data-size="${savedSize}"]`)?.classList.add('active');

    // Carregar contraste
    const savedContrast = localStorage.getItem('contrast') || 'normal';
    if (savedContrast === 'high') {
        document.body.classList.add('high-contrast');
        document.querySelector('.contrast-button[data-contrast="high"]')?.classList.add('active');
    }

    // Carregar outras preferências
    const animations = localStorage.getItem('disableAnimations') === 'true';
    const textSpacing = localStorage.getItem('textSpacing') === 'true';
    const dyslexicFont = localStorage.getItem('dyslexicFont') === 'true';

    document.getElementById('animationsToggle').checked = animations;
    document.getElementById('textSpacingToggle').checked = textSpacing;
    document.getElementById('dyslexicFontToggle').checked = dyslexicFont;

    if (animations) document.body.classList.add('no-animations');
    if (textSpacing) document.body.classList.add('increased-spacing');
    if (dyslexicFont) document.body.classList.add('dyslexic-font');
}

function setupTextSizeButtons() {
    const sizeButtons = document.querySelectorAll('.size-button');
    
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
    
    contrastButtons.forEach(button => {
        button.addEventListener('click', () => {
            contrastButtons.forEach(btn => btn.classList.remove('active'));
            const contrast = button.dataset.contrast;
            
            if (contrast === 'high') {
                document.body.classList.add('high-contrast');
                button.classList.add('active');
            } else {
                document.body.classList.remove('high-contrast');
                document.querySelector('.contrast-button[data-contrast="normal"]').classList.add('active');
            }
            
            localStorage.setItem('contrast', contrast);
        });
    });
}

function setupAccessibilityToggles() {
    // Desativar animações
    const animationsToggle = document.getElementById('animationsToggle');
    animationsToggle.addEventListener('change', function() {
        document.body.classList.toggle('no-animations', this.checked);
        localStorage.setItem('disableAnimations', this.checked);
    });

    // Aumentar espaçamento
    const textSpacingToggle = document.getElementById('textSpacingToggle');
    textSpacingToggle.addEventListener('change', function() {
        document.body.classList.toggle('increased-spacing', this.checked);
        localStorage.setItem('textSpacing', this.checked);
    });

    // Fonte para dislexia
    const dyslexicFontToggle = document.getElementById('dyslexicFontToggle');
    dyslexicFontToggle.addEventListener('change', function() {
        document.body.classList.toggle('dyslexic-font', this.checked);
        localStorage.setItem('dyslexicFont', this.checked);
    });
}

function setupKeyboardShortcuts() {
    document.addEventListener('keydown', function(e) {
        // Alt + número para navegação
        if (e.altKey && !e.ctrlKey && !e.shiftKey) {
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
                    window.location.href = 'profile.html';
                    break;
            }
        }
        
        // Barra para focar na pesquisa (em outras páginas)
        if (e.key === '/' && document.querySelector('.search-input')) {
            e.preventDefault();
            document.querySelector('.search-input').focus();
        }
    });
}