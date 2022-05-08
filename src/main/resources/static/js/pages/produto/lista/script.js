var url = new URL(window.location.href)

$(function() {
    getPaginas();
    getAbaSelecionada()
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

function selecionaAba(tipoProduto) {
    url.searchParams.set('tipoProduto', tipoProduto.toLowerCase());
    url.searchParams.set('pagina', '0');
    window.location.replace(url)
}

function getPaginas() {
    let controladorPaginas = $('.pagination li a');
    controladorPaginas.eq(2).text(paginacao.number + 1);
    if (paginacao.number === 0) {
        $('#pagina-anterior').addClass('disabled');
    }
    if (paginacao.totalPages - 1 === paginacao.number) {
        $('#pagina-seguinte').addClass('disabled');
    }
}


function alteraPagina(numeroPagina) {
    if (numeroPagina >= 0 && numeroPagina <= paginacao.totalPages - 1) {
        url.searchParams.set("pagina", numeroPagina)
        window.location.replace(url)
    }
}
