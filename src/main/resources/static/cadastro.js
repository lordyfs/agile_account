document.getElementById('cadastroForm').addEventListener('submit', function(e) {
    e.preventDefault();

    // Pegar os valores dos campos
    const nome = document.getElementById('nome').value;
    const email = document.getElementById('email').value;
    const cpf = document.getElementById('cpf').value;
    const senha = document.getElementById('senha').value;

    // Criar objeto com os dados do usuário
    const usuario = {
        nome: nome,
        email: email,
        cpf: cpf,
        senha: senha
    };

    try {
        // Salvar no localStorage
        localStorage.setItem('usuarioCadastrado', JSON.stringify(usuario));
        
        document.getElementById('mensagem').style.color = 'green';
        document.getElementById('mensagem').textContent = 'Cadastro realizado com sucesso!';
        
        // Redirecionar para a página de login após 2 segundos
        setTimeout(() => {
            window.location.href = 'login.html';
        }, 2000);
    } catch (error) {
        document.getElementById('mensagem').style.color = 'red';
        document.getElementById('mensagem').textContent = 'Erro ao cadastrar. Tente novamente.';
    }
});
