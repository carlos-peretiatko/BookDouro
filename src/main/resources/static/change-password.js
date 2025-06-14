document.addEventListener('DOMContentLoaded', () => {
    const changePasswordForm = document.getElementById('changePasswordForm');
    
    function showMessage(message, isError = false) {
        const existingMessage = document.querySelector('.message');
        if (existingMessage) {
            existingMessage.remove();
        }

        const messageDiv = document.createElement('div');
        messageDiv.className = `message ${isError ? 'error-message' : 'success-message'}`;
        messageDiv.innerHTML = `<i class="fas fa-${isError ? 'exclamation-circle' : 'check-circle'}"></i>${message}`;
        changePasswordForm.insertBefore(messageDiv, changePasswordForm.querySelector('button'));
    }

    changePasswordForm.addEventListener('submit', async (e) => {
        e.preventDefault();

        const currentPassword = document.getElementById('currentPassword').value;
        const newPassword = document.getElementById('newPassword').value;
        const confirmPassword = document.getElementById('confirmPassword').value;
        const userId = localStorage.getItem('userId');

        if (!userId) {
            showMessage('Usuário não identificado. Por favor, faça login novamente.', true);
            setTimeout(() => {
                window.location.href = 'login.html';
            }, 2000);
            return;
        }

        if (newPassword !== confirmPassword) {
            showMessage('As senhas não coincidem', true);
            return;
        }

        const submitButton = changePasswordForm.querySelector('button[type="submit"]');
        const originalButtonText = submitButton.innerHTML;

        try {
            submitButton.disabled = true;
            submitButton.innerHTML = '<i class="fas fa-spinner fa-spin"></i>Alterando...';

            const response = await fetch(`http://localhost:8080/api/clientes/${userId}/change-password`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    senhaAtual: currentPassword,
                    novaSenha: newPassword
                })
            });

            if (!response.ok) {
                const errorText = await response.text();
                throw new Error(errorText || 'Erro ao alterar senha');
            }

            showMessage('Senha alterada com sucesso!');
            
            // Limpa os campos
            changePasswordForm.reset();
            
            // Redireciona após 2 segundos
            setTimeout(() => {
                window.location.href = 'profile.html';
            }, 2000);

        } catch (error) {
            console.error('Erro:', error);
            showMessage(error.message || 'Erro ao conectar com o servidor', true);
        } finally {
            submitButton.disabled = false;
            submitButton.innerHTML = originalButtonText;
        }
    });
});