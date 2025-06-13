document.getElementById('loginForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;
    const mensagem = document.getElementById('mensagem');

    try {
        const response = await fetch('http://localhost:8099/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                email: email,
                senha: senha
            })
        });

        if (response.status === 200) {
            const data = await response.json();
            console.log(data);
            sessionStorage.setItem('Usuario', JSON.stringify(data));
            //let usr = sessionStorage.getItem('Usuario');
            window.location.href = 'TelaPrincipal.html';
        }
        
    } catch (error) {
        console.error('Erro ao fazer login:', error);
    }

   // console.log(json);


    // Verificar credenciais de admin
    //const emailValido = 'admin@admin';
    //const senhaValida = 'admin';

    // Verificar credenciais do usuário cadastrado
    //const usuarioCadastrado = JSON.parse(localStorage.getItem('usuarioCadastrado')) || {};

    /*if (email === emailValido && senha === senhaValida) {
        window.location.href = 'TelaPrincipal.html';
    }    // Verificar se as credenciais correspondem ao usuário cadastrado
    else if (email === usuarioCadastrado.email && senha === usuarioCadastrado.senha) {
        // Salvar o nome do usuário logado
        localStorage.setItem('usuarioLogado', usuarioCadastrado.nome);
        window.location.href = 'TelaPrincipal.html';
    } else {
        mensagem.textContent = 'E-mail ou senha incorretos.';
    }*/
});


