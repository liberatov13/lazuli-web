$(function () {
    $('#input-produto-final').autocomplete({
        source: ['Teste', 'Teste2', 'Teste3']
    })
})

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
