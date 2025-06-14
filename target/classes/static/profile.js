document.addEventListener('DOMContentLoaded', async () => {
    // Referências aos elementos
    const profileForm = document.getElementById('profileForm');
    const editButton = document.getElementById('editButton');
    const saveButton = document.getElementById('saveButton');
    const cancelButton = document.getElementById('cancelButton');
    const changePasswordButton = document.getElementById('changePasswordButton');
    const logoutButton = document.getElementById('logoutButton');
    const deleteAccountButton = document.getElementById('deleteAccountButton');
    const deleteModal = document.getElementById('deleteModal');
    const confirmDelete = document.getElementById('confirmDelete');
    const cancelDelete = document.getElementById('cancelDelete');
    const inputs = profileForm.querySelectorAll('input');

    let originalData = {};

    // Função para carregar dados do perfil
    async function loadProfileData() {
        try {
            const cpf = localStorage.getItem('userCpf');
            const response = await fetch(`http://localhost:8080/api/clientes/cpf/${cpf}`);
            if (!response.ok) throw new Error('Erro ao carregar dados do perfil');
            
            const userData = await response.json();
            updateProfileUI(userData);
            originalData = { ...userData }; // Guardar cópia dos dados originais
            // Guardar o ID para atualização
            localStorage.setItem('userId', userData.id);
        } catch (error) {
            console.error('Erro:', error);
            alert('Erro ao carregar dados do perfil');
        }
    }

    // Função para atualizar a UI com os dados do usuário
    function updateProfileUI(userData) {
        document.getElementById('nome').value = userData.nome || '';
        document.getElementById('cpf').value = userData.cpf || '';
        document.getElementById('email').value = userData.email || '';
        document.getElementById('telefone').value = userData.telefone || '';
    }

    // Função para habilitar edição
    function enableEditing() {
        inputs.forEach(input => {
            if (input.id !== 'cpf') { // CPF não pode ser editado
                input.readOnly = false;
            }
        });
        editButton.style.display = 'none';
        saveButton.style.display = 'block';
        cancelButton.style.display = 'block';
    }

    // Função para desabilitar edição
    function disableEditing() {
        inputs.forEach(input => input.readOnly = true);
        editButton.style.display = 'block';
        saveButton.style.display = 'none';
        cancelButton.style.display = 'none';
    }

    // Função para atualizar o perfil
    async function updateProfile() {
        try {
            const userId = localStorage.getItem('userId');
            if (!userId) {
                throw new Error('ID do usuário não encontrado');
            }

            const formData = {
                nome: document.getElementById('nome').value,
                email: document.getElementById('email').value,
                telefone: document.getElementById('telefone').value,
                cpf: document.getElementById('cpf').value,
                id: userId
            };

            const response = await fetch(`http://localhost:8080/api/clientes/${userId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            });

            if (!response.ok) {
                const errorData = await response.text();
                throw new Error(errorData || 'Erro ao atualizar perfil');
            }

            const updatedData = await response.json();
            updateProfileUI(updatedData);
            originalData = { ...updatedData };
            disableEditing();
            alert('Perfil atualizado com sucesso!');
        } catch (error) {
            console.error('Erro:', error);
            alert('Erro ao atualizar perfil: ' + error.message);
        }
    }

    // Função para excluir conta
    async function deleteAccount() {
        try {
            const userId = localStorage.getItem('userId');
            if (!userId) {
                throw new Error('ID do usuário não encontrado');
            }

            const response = await fetch(`http://localhost:8080/api/clientes/${userId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            if (!response.ok) {
                const errorData = await response.text();
                throw new Error(errorData || 'Erro ao excluir conta');
            }

            // Limpar dados do usuário do localStorage
            localStorage.removeItem('userCpf');
            localStorage.removeItem('userName');
            localStorage.removeItem('userId');
            
            // Redirecionar para a página de login
            window.location.href = 'login.html';
        } catch (error) {
            console.error('Erro:', error);
            alert('Erro ao excluir conta: ' + error.message);
        }
    }

    // Event Listeners
    editButton.addEventListener('click', enableEditing);

    saveButton.addEventListener('click', updateProfile);

    cancelButton.addEventListener('click', () => {
        updateProfileUI(originalData);
        disableEditing();
    });

    changePasswordButton.addEventListener('click', () => {
        window.location.href = 'change-password.html';
    });

    logoutButton.addEventListener('click', () => {
        // Limpa os dados do usuário do localStorage
        localStorage.removeItem('userCpf');
        localStorage.removeItem('userName');
        localStorage.removeItem('userId');
        
        // Redireciona para a página de login
        window.location.href = 'login.html';
    });

    deleteAccountButton.addEventListener('click', () => {
        deleteModal.style.display = 'block';
    });

    confirmDelete.addEventListener('click', deleteAccount);

    cancelDelete.addEventListener('click', () => {
        deleteModal.style.display = 'none';
    });

    // Fechar modal quando clicar fora
    window.addEventListener('click', (e) => {
        if (e.target === deleteModal) {
            deleteModal.style.display = 'none';
        }
    });

    // Inicializar página
    loadProfileData();
});