var url = new URL(window.location.href);

$(function() {
    atualizaControladorDePaginacao();
    getAbaSelecionada();
    $('[data-bs-toggle="tooltip"]').tooltip();
})

function getAbaSelecionada() {
    switch (tipoProduto.toUpperCase()) {
        case 'VENDA':
            $('#venda-link').addClass('active');
            break;
        case 'INGREDIENTE':
            $('#ingrediente-link').addClass('active');
            break;
    }
}

function redirecionarTelaCadastroProduto() {
    url = window.location.origin + "/produto/cadastrar"
    window.location.replace(url)
}

function setQuantidadeItensPorPagina(quantidadeItemPorPagina) {
    url.searchParams.set('conteudoPorPagina', quantidadeItemPorPagina);
    window.location.replace(url)
}

function alterarAbaTipoProdutoSelecionada(nomeTipoProduto) {
    var path = url.pathname.split("/");
    path[path.length -1] = nomeTipoProduto.toLowerCase();
    url.pathname = path.join("/");
    url.searchParams.set('pagina', '0');
    window.location.replace(url);
}

function atualizaControladorDePaginacao() {
    let controladorPaginas = $('#controlador-pagina-atual');
    controladorPaginas.text(paginacao.number + 1);
    if (paginacao.number === 0) {
        $('#li-pagina-anterior').addClass('disabled');
    }
    if (paginacao.totalPages - 1 === paginacao.number) {
        $('#li-pagina-seguinte').addClass('disabled');
    }
}

function alteraPagina(numeroPagina) {
    if (numeroPagina >= 0 && numeroPagina <= paginacao.totalPages - 1) {
        url.searchParams.set("pagina", numeroPagina)
        window.location.replace(url)
    }
}
