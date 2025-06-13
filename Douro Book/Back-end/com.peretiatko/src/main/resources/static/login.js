// login.js

document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('loginForm');
    if (!loginForm) {
        console.error('Formulário de login não encontrado!');
        return;
    }

    // Função para mostrar mensagem de erro
    function showError(message) {
        // Remove qualquer mensagem de erro anterior
        const existingError = document.querySelector('.error-message');
        if (existingError) {
            existingError.remove();
        }

        // Cria e insere a nova mensagem de erro
        const errorDiv = document.createElement('div');
        errorDiv.className = 'error-message';
        errorDiv.innerHTML = `<i class="fas fa-exclamation-circle"></i>${message}`;
        loginForm.insertBefore(errorDiv, loginForm.querySelector('button'));
    }

    loginForm.addEventListener('submit', async (e) => {
        e.preventDefault();

        // Remove mensagem de erro anterior se existir
        const existingError = document.querySelector('.error-message');
        if (existingError) {
            existingError.remove();
        }

        try {
            const formData = new FormData(loginForm);
            const email = formData.get('email')?.trim();
            const senha = formData.get('senha')?.trim();

            if (!email || !senha) {
                showError('Email e senha são obrigatórios');
                return;
            }

            // Desabilita o botão durante o login
            const submitButton = loginForm.querySelector('button[type="submit"]');
            const originalButtonText = submitButton.innerHTML;
            submitButton.disabled = true;
            submitButton.innerHTML = '<i class="fas fa-spinner fa-spin"></i>Entrando...';

            const loginData = { email, senha };
            console.log('Tentando fazer login...');

            const response = await fetch('http://localhost:8080/api/clientes/login', {
                method: 'POST',
                headers: { 
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
                body: JSON.stringify(loginData)
            });
            
            const responseText = await response.text();
            console.log('Resposta do servidor:', responseText);

            if (!response.ok) {
                if (response.status === 401) {
                    throw new Error('Email ou senha incorretos');
                } else if (response.status === 404) {
                    throw new Error('Usuário não encontrado');
                }
                throw new Error(responseText || 'Erro ao fazer login');
            }

            const user = responseText ? JSON.parse(responseText) : null;
            if (!user) {
                throw new Error('Resposta inválida do servidor');
            }

            console.log('Login bem-sucedido:', user.nome);
            localStorage.setItem('userCpf', user.cpf);
            localStorage.setItem('userName', user.nome);
            
            // Mostra mensagem de sucesso antes de redirecionar
            const successDiv = document.createElement('div');
            successDiv.className = 'success-message';
            successDiv.innerHTML = '<i class="fas fa-check-circle"></i>Login bem-sucedido! Redirecionando...';
            loginForm.insertBefore(successDiv, loginForm.querySelector('button'));

            // Redireciona após um pequeno delay
            setTimeout(() => {
                window.location.href = 'home.html';
            }, 1000);

        } catch (error) {
            console.error('Erro detalhado:', error);
            showError(error.message || 'Erro ao conectar com o servidor');
        } finally {
            // Restaura o botão
            const submitButton = loginForm.querySelector('button[type="submit"]');
            submitButton.disabled = false;
            submitButton.innerHTML = '<i class="fas fa-sign-in-alt"></i>Entrar';
        }
    });
});
