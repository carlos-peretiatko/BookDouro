document.addEventListener('DOMContentLoaded', () => {
    // Função para adicionar eventos de seta e clique nos cards
    function addSliderEvents(slider) {
        const bookList = slider.querySelector('.book-list');
        const leftArrow = slider.querySelector('.slider-arrow.left');
        const rightArrow = slider.querySelector('.slider-arrow.right');

        if (leftArrow && bookList) {
            leftArrow.addEventListener('click', () => {
                bookList.scrollBy({
                    left: -300,
                    behavior: 'smooth'
                });
            });
        }
        if (rightArrow && bookList) {
            rightArrow.addEventListener('click', () => {
                bookList.scrollBy({
                    left: 300,
                    behavior: 'smooth'
                });
            });
        }
    }

    function addCardClickEvents(context=document) {
        context.querySelectorAll('.book-card').forEach(card => {
            card.style.cursor = 'pointer';
            card.addEventListener('click', () => {
                // Aqui você pode pegar o ID real do livro de algum atributo data-*
                const bookId = card.dataset.id || '1'; // Exemplo
                window.location.href = `book-details.html?id=${bookId}`;
            });
        });
    }

    // Inicializar sliders e cards existentes
    document.querySelectorAll('.book-slider').forEach(addSliderEvents);
    addCardClickEvents();

    // Botão "Voltar ao Topo"
    const backToTopBtn = document.createElement('button');
    backToTopBtn.className = 'back-to-top';
    backToTopBtn.innerHTML = '↑';
    document.body.appendChild(backToTopBtn);

    const content = document.querySelector('.content');
    content.addEventListener('scroll', () => {
        if (content.scrollTop > 500) {
            backToTopBtn.classList.add('visible');
        } else {
            backToTopBtn.classList.remove('visible');
        }
    });

    backToTopBtn.addEventListener('click', () => {
        content.scrollTo({
            top: 0,
            behavior: 'smooth'
        });
    });

    // Infinite scroll
    let isLoading = false;
    content.addEventListener('scroll', () => {
        if (isLoading) return;
        const scrollPosition = content.scrollTop + content.clientHeight;
        const totalHeight = content.scrollHeight;
        if (scrollPosition >= totalHeight - 100) {
            isLoading = true;
            loadMoreContent();
        }
    });

    function loadMoreContent() {
        setTimeout(() => {
            const categories = [
                'Aventura', 'Romance', 'Terror',
                'Ficção', 'Filosofia', 'Educacionais', 'Arte'
            ];
            const randomCategory = categories[Math.floor(Math.random() * categories.length)];
            const newSection = createBookSection(randomCategory);
            content.appendChild(newSection);

            // Adicionar eventos nas novas seções/cards
            addSliderEvents(newSection.querySelector('.book-slider'));
            addCardClickEvents(newSection);

            isLoading = false;
        }, 1000);
    }

    function createBookSection(category) {
        const section = document.createElement('section');
        section.className = 'book-section';
        if (category === 'Terror') {
            section.innerHTML = `
            <h2>${category}:</h2>
            <div class="book-slider">
                <button class="slider-arrow left">‹</button>
                <div class="book-list">
                    <div class="book-card"><img src="https://m.media-amazon.com/images/I/71uPPT8QB2L._AC_UL320_.jpg" alt="Livro"></div>
                    <div class="book-card"><img src="https://m.media-amazon.com/images/I/81PUWRRbMhL._AC_UL320_.jpg" alt="Livro"></div>
                    <div class="book-card"><img src="https://m.media-amazon.com/images/I/71p3pBlp+JL._AC_UL320_.jpg" alt="Livro"></div>
                    <div class="book-card"><img src="https://m.media-amazon.com/images/I/81j3K2LUfcL._AC_UL320_.jpg" alt="Livro"></div>
                    <div class="book-card"><img src="https://m.media-amazon.com/images/I/81Jz5PrvZFL._AC_UL320_.jpg" alt="Livro"></div>
                    <div class="book-card"><img src="https://m.media-amazon.com/images/I/71LBtlVGqHL._AC_UL320_.jpg" alt="Livro"></div>
                    <div class="book-card"><img src="https://m.media-amazon.com/images/I/81JaIN9NExL._AC_UL320_.jpg" alt="Livro"></div>
                    <div class="book-card"><img src="https://m.media-amazon.com/images/I/81cV9cmMfpL._AC_UL320_.jpg" alt="Livro"></div>
                    <div class="book-card"><img src="https://m.media-amazon.com/images/I/6191JdWZqfL._AC_UL320_.jpg" alt="Livro"></div>
                    <div class="book-card"><img src="https://m.media-amazon.com/images/I/71tUvLm7SmL._AC_UL320_.jpg" alt="Livro"></div>
                </div>
                <button class="slider-arrow right">›</button>
            </div>
        `;
            return section;
        } else if (category === 'Aventura') {
            section.innerHTML = `
                <h2>${category}:</h2>
                <div class="book-slider">
                    <button class="slider-arrow left">‹</button>
                    <div class="book-list">
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/81N-C60M53L._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/71UKvlLXALL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/91BdHMR+q0L._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/81GZ1J+oeJL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/91mULk6w-cL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/91M9xPIf10L._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/71BCO5zrH1L._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/91sPu1ukUtL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/91915km0UdL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/61VKYk9y4dL._AC_UL320_.jpg" alt="Livro"></div>
                    </div>
                    <button class="slider-arrow right">›</button>
                </div>
            `;
            return section;
        } else if (category === 'Romance') {
            section.innerHTML = `
                <h2>${category}:</h2>
                <div class="book-slider">
                    <button class="slider-arrow left">‹</button>
                    <div class="book-list">
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/71DPuiRWNqL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/61NIKTSzi1L._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/71KrPChpFXL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/81w-GCfqtjL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/61qs2JCP4NL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/81Jo8UAsqOL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/71PJ9yPc9nL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/81iUrbors9L._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/61uj5fW9-lL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/8151svfx-9L._AC_UL320_.jpg" alt="Livro"></div>
                    </div>
                    <button class="slider-arrow right">›</button>
                </div>
            `;
            return section;
        //fim do romance 
        } else if(category === 'Ficção'){
            section.innerHTML = `
                <h2>${category}:</h2>
                <div class="book-slider">
                    <button class="slider-arrow left">‹</button>
                    <div class="book-list">
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/71Fm+YuSYML._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/91AHNAr638L._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/61VvC7yP8dL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/71OUpEYLKkL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/81JEoeOD+4L._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/81myqvLjbeL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/912LLkkhCaS._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/51k4BFdjc3L._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/51tAD6LyZ-L._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/61ooZHl+CTL._AC_UL320_.jpg" alt="Livro"></div>
                    </div>
                    <button class="slider-arrow right">›</button>
                </div>
            `;
            return section;
        //fim da ficção
        } else if(category === 'Filosofia'){
            section.innerHTML = `
                <h2>${category}:</h2>
                <div class="book-slider">
                    <button class="slider-arrow left">‹</button>
                    <div class="book-list">
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/81Of28SM4DL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/81lkDxJvpXL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/71uR7WDBLgL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/91hv2cST8ZL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/51TgheB19FL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/61N9GLW2AyS._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/91yZvJ40FJL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/81EfKoowGJL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/812BaW4MacL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/71F-Uf20+UL._AC_UL320_.jpg" alt="Livro"></div>
                    </div>
                    <button class="slider-arrow right">›</button>
                </div>
            `;
            return section;
            //fim da filosofia
        } else if(category === 'Educacionais'){
            section.innerHTML = `
                <h2>${category}:</h2>
                <div class="book-slider">
                    <button class="slider-arrow left">‹</button>
                    <div class="book-list">
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/810WJVEUvvL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/81mj4JaCOzL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/81gXbrZlIgL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/81VmxoSoHDL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/71wbqCbmrBL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/71xl-x4uLQL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/71eOl75qthL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/71ugTUNNw5L._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/811ny+ez9HL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/81m4eP8XO6L._AC_UL320_.jpg" alt="Livro"></div>
                    </div>
                    <button class="slider-arrow right">›</button>
                </div>
            `;
            return section;
            //fim dos educacionais
        } else {
            section.innerHTML = `
                <h2>${category}:</h2>
                <div class="book-slider">
                    <button class="slider-arrow left">‹</button>
                    <div class="book-list">
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/71DmLK1jLVL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/91mYAYCfGNL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/A1UA9anggXL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/61dOT2AYdUL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/819P25ga3TL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/A1jPodynbfL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/81r0MEuQNbL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/71gmDsrco-L._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/71F-UqfmQqL._AC_UL320_.jpg" alt="Livro"></div>
                        <div class="book-card"><img src="https://m.media-amazon.com/images/I/61d4is+1wsL._AC_UL320_.jpg" alt="Livro"></div>
                    </div>
                    <button class="slider-arrow right">›</button>
                </div>
            `;
            return section;
            //fim das artes
        }
            
    }
});