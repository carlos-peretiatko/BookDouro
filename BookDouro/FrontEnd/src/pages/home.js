document.addEventListener('DOMContentLoaded', () => {
    const leftArrows = document.querySelectorAll('.arrow.left');
    const rightArrows = document.querySelectorAll('.arrow.right');

    leftArrows.forEach((arrow, index) => {
        arrow.addEventListener('click', () => scrollBooks(index, -1));
    });

    rightArrows.forEach((arrow, index) => {
        arrow.addEventListener('click', () => scrollBooks(index, 1));
    });
});

function scrollBooks(index, direction) {
    const bookLists = document.querySelectorAll('.book-list');
    const bookList = bookLists[index];
    const scrollAmount = 200; // Pixels to scroll
    bookList.scrollBy({
        left: scrollAmount * direction,
        behavior: 'smooth',
    });
}

//setas
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