// register.js

document.addEventListener('DOMContentLoaded', () => {
    const registerForm = document.getElementById('registerForm');
    if (!registerForm) {
        console.error('Formulário de registro não encontrado!');
        return;
    }

    registerForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const formData = new FormData(registerForm);
        const data = Object.fromEntries(formData.entries());
        
        // Log dos dados que serão enviados
        console.log('Dados do formulário:', data);

        try {
            const response = await fetch('http://localhost:8080/api/clientes/cadastro', {
                method: 'POST',
                headers: { 
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
                body: JSON.stringify(data)
            });
            
            console.log('Status da resposta:', response.status);
            
            // Tentar ler o corpo da resposta
            const responseText = await response.text();
            console.log('Resposta do servidor:', responseText);
            
            if (!response.ok) {
                throw new Error(responseText || 'Erro ao registrar usuário');
            }

            alert('Cadastro realizado com sucesso! Faça login.');
            window.location.href = 'login.html';
        } catch (error) {
            console.error('Erro completo:', error);
            alert('Erro ao registrar: ' + (error.message || 'Erro desconhecido'));
        }
    });
});
