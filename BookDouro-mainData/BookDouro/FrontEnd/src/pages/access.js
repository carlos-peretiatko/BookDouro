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