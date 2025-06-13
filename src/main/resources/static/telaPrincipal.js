// Elementos do cabeçalho
const welcomeMessage = document.getElementById('welcomeMessage');

// Elementos relacionados ao valor mensal
const valorMensalCard = document.getElementById('valorMensalCard');
const valorMensalDisplay = document.getElementById('valorMensalDisplay');
const valorExibido = document.getElementById('valorExibido');
const inputValorMensal = document.getElementById('valorMensalInput');

// Botões de ação para valor mensal
const btnSalvarValorMensal = document.getElementById('salvarValorMensal');
const btnEditarValor = document.getElementById('editarValor');

// Elementos relacionados às despesas
const descricaoInput = document.getElementById('descricao');
const valorInput = document.getElementById('valor');
const btnAdicionarDespesa = document.getElementById('adicionarDespesa');
const tabelaBody = document.querySelector('#tabelaDespesas tbody');

// Funções utilitárias
function formatarMoeda(valor) {
    return parseFloat(valor).toFixed(2);
}

function validarNumero(valor) {
    return !isNaN(valor) && valor >= 0;
}

// Funções de gerenciamento de saldo
function checkLowBalance(valorMensalAtual) {
    const valorInicial = localStorage.getItem('valorInicial');
    if (valorInicial) {
        const porcentagemGasta = 100 - ((valorMensalAtual * 100) / parseFloat(valorInicial));
        
        if (porcentagemGasta >= 90) {
            alert('ATENÇÃO! Você já gastou 90% do seu saldo mensal!');
        } else if (porcentagemGasta >= 75) {
            alert('CUIDADO! Você já utilizou 75% do seu saldo mensal.');
        } else if (porcentagemGasta >= 50) {
            alert('Aviso: Você já utilizou metade do seu saldo mensal.');
        }
    }
}

// Funções de gerenciamento de valor mensal
function carregarValorMensal() {
    try {
        const valorMensal = localStorage.getItem('valorMensal');
        if (valorMensal && validarNumero(valorMensal)) {
            valorExibido.textContent = formatarMoeda(valorMensal);
            valorMensalCard.style.display = 'none';
            valorMensalDisplay.style.display = 'block';
            checkLowBalance(parseFloat(valorMensal));
        }
    } catch (error) {
        console.error('Erro ao carregar valor mensal:', error);
        alert('Erro ao carregar valor mensal. Por favor, tente novamente.');
    }
}

function salvarValorMensal(valor) {
    try {
        if (!validarNumero(valor)) {
            throw new Error('Valor inválido');
        }
        localStorage.setItem('valorMensal', formatarMoeda(valor));
        if (!localStorage.getItem('valorInicial')) {
            localStorage.setItem('valorInicial', formatarMoeda(valor));
        }
        carregarValorMensal();
    } catch (error) {
        console.error('Erro ao salvar valor mensal:', error);
        alert('Erro ao salvar valor mensal. Por favor, tente novamente.');
    }
}

// Funções de gerenciamento de despesas
function carregarDespesas() {
    try {
        const despesas = JSON.parse(localStorage.getItem('despesas')) || [];
        tabelaBody.innerHTML = '';
        despesas.forEach(despesa => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${despesa.descricao}</td>
                <td>R$ ${formatarMoeda(despesa.valor)}</td>
                <td>
                    <div class="expense-actions">
                        <button class="btn-editar">Editar</button>
                        <button class="btn-remover">Remover</button>
                    </div>
                </td>
            `;
            tabelaBody.appendChild(tr);
        });
    } catch (error) {
        console.error('Erro ao carregar despesas:', error);
        alert('Erro ao carregar despesas. Por favor, atualize a página.');
    }
}

function adicionarDespesa(descricao, valor) {
    try {
        if (!descricao || !validarNumero(valor)) {
            throw new Error('Dados inválidos');
        }

        let valorMensalAtual = parseFloat(localStorage.getItem('valorMensal'));
        if (valor > valorMensalAtual) {
            throw new Error('Saldo insuficiente');
        }

        const despesas = JSON.parse(localStorage.getItem('despesas')) || [];
        const novaDespesa = {
            descricao: descricao,
            valor: formatarMoeda(valor)
        };

        despesas.push(novaDespesa);
        localStorage.setItem('despesas', JSON.stringify(despesas));

        valorMensalAtual -= valor;
        localStorage.setItem('valorMensal', formatarMoeda(valorMensalAtual));
        valorExibido.textContent = formatarMoeda(valorMensalAtual);
        checkLowBalance(valorMensalAtual);

        return true;
    } catch (error) {
        console.error('Erro ao adicionar despesa:', error);
        alert(error.message === 'Saldo insuficiente' ? 
            'Saldo insuficiente para essa despesa.' : 
            'Erro ao adicionar despesa. Verifique os dados e tente novamente.');
        return false;
    }
}

// Função para exibir mensagem de boas-vindas
function carregarNomeUsuario() {
    const nomeCompleto = localStorage.getItem('usuarioLogado');
    if (nomeCompleto) {
        // Pega apenas o primeiro nome
        const primeiroNome = nomeCompleto.split(' ')[0];
        welcomeMessage.textContent = `${primeiroNome} SEJA BEM VINDO AO AGILLE ACCOUNT`;
    }
}

// Event Listeners
btnSalvarValorMensal.addEventListener('click', () => {
    const valorMensal = inputValorMensal.value.trim();
    if (valorMensal === '') {
        alert('Por favor, insira um valor mensal.');
        return;
    }
    if (salvarValorMensal(parseFloat(valorMensal))) {
        inputValorMensal.value = '';
    }
});

btnEditarValor.addEventListener('click', () => {
    const valorAtual = valorExibido.textContent;
    inputValorMensal.value = valorAtual;
    valorMensalDisplay.style.display = 'none';
    valorMensalCard.style.display = 'block';
});

btnAdicionarDespesa.addEventListener('click', () => {
    const descricao = descricaoInput.value.trim();
    const valor = valorInput.value.trim();

    if (adicionarDespesa(descricao, parseFloat(valor))) {
        carregarDespesas();
        descricaoInput.value = '';
        valorInput.value = '';
    }
});

tabelaBody.addEventListener('click', (e) => {
    const tr = e.target.closest('tr');
    if (!tr) return;
    
    try {
        const descricaoAntiga = tr.children[0].textContent;
        const valorAntigo = parseFloat(tr.children[1].textContent.replace('R$', '').trim());
        let despesas = JSON.parse(localStorage.getItem('despesas')) || [];

        if (e.target.classList.contains('btn-remover')) {
            despesas = despesas.filter(d => d.descricao !== descricaoAntiga);
            localStorage.setItem('despesas', JSON.stringify(despesas));

            let valorMensalAtual = parseFloat(localStorage.getItem('valorMensal'));
            valorMensalAtual += valorAntigo;
            localStorage.setItem('valorMensal', formatarMoeda(valorMensalAtual));
            valorExibido.textContent = formatarMoeda(valorMensalAtual);
            checkLowBalance(valorMensalAtual);

            carregarDespesas();
        }

        if (e.target.classList.contains('btn-editar')) {
            const novaDescricao = prompt('Editar descrição:', descricaoAntiga);
            const novoValorStr = prompt('Editar valor:', formatarMoeda(valorAntigo));

            if (novaDescricao && novoValorStr) {
                const novoValor = parseFloat(novoValorStr);
                if (!validarNumero(novoValor)) {
                    throw new Error('Valor inválido');
                }

                const diff = novoValor - valorAntigo;
                let valorMensalAtual = parseFloat(localStorage.getItem('valorMensal'));
                
                if (diff > valorMensalAtual) {
                    throw new Error('Saldo insuficiente');
                }

                const index = despesas.findIndex(d => d.descricao === descricaoAntiga);
                if (index !== -1) {
                    despesas[index] = {
                        descricao: novaDescricao,
                        valor: formatarMoeda(novoValor)
                    };
                    localStorage.setItem('despesas', JSON.stringify(despesas));

                    valorMensalAtual -= diff;
                    localStorage.setItem('valorMensal', formatarMoeda(valorMensalAtual));
                    valorExibido.textContent = formatarMoeda(valorMensalAtual);
                    checkLowBalance(valorMensalAtual);

                    carregarDespesas();
                }
            }
        }
    } catch (error) {
        console.error('Erro ao gerenciar despesa:', error);
        alert(error.message === 'Saldo insuficiente' ? 
            'Saldo insuficiente para essa alteração.' : 
            'Erro ao gerenciar despesa. Por favor, tente novamente.');
    }
});

// Inicialização
document.addEventListener('DOMContentLoaded', () => {
    carregarNomeUsuario();
    carregarValorMensal();
    carregarDespesas();
});