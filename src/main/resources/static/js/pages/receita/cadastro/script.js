$(function () {
    $('#input-produto-final').autocomplete({
        source: function (request, response) {
            buscarProdutoAPI(request, response);
        },
        minLength: 2
    })
})

function buscarProdutoAPI(produtoBuscado, resposta) {
    $.ajax('/api/produtos?busca='+produtoBuscado.term, {
        success: function (resultado) {
            let descricoes = [];
            resultado.forEach(produto => {
                    descricoes.push({
                        value: produto.idProduto,
                        label: produto.idProduto + ' - ' + produto.descricaoBasica
                    })
            })
            resposta(descricoes)
        }
    })
}

function selecionarProdutoFinal() {
    let inputProdutoSelecionado = $('#input-produto-final-selecionado');
    inputProdutoSelecionado.val($('input[name=produto-selecionado]:checked').val());
    $('#input-produto-final').hide();
    inputProdutoSelecionado.show();
    $('#modal-produtos-encontrados').modal('hide');
}

function gerarModalProdutosEncontrados(resultado) {
    const modal = new bootstrap.Modal('#modal-produtos-encontrados');
    let tabelaProdutosEncontrados =
        '<table class="table">' +
        '   <thead>' +
        '       <tr>' +
        '           <th>#</th>' +
        '           <th>Descrição</th>' +
        '           <th></th>' +
        '       </tr>' +
        '   </thead>' +
        '   <tbody>';
    resultado.forEach(produto => {
        tabelaProdutosEncontrados +=
            '       <tr>' +
            '           <td>' + produto.idProduto + '</td>' +
            '           <td>' + produto.descricaoBasica + '</td>' +
            '           <td>' +
            '               <input type="radio" class="form-check-input" name="produto-selecionado" value="' + produto + '" ' +
            '           </td>' +
            '       </tr>'
    });
    tabelaProdutosEncontrados +=
        '   </tbody>' +
        '</table>'

    $('.modal-body').html(tabelaProdutosEncontrados);
    modal.show();
}

function buscarProduto() {
    var produtoBuscado = $('#input-produto-final').val()
    $.ajax('/api/produtos?busca='+produtoBuscado, {
        success: function (resultado) {
            gerarModalProdutosEncontrados(resultado);
        }
    })
}
 function removerProdutoFinal() {
     receita.produtoFinal = null;
 }
