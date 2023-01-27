$(function () {
    $('#input-produto-final').autocomplete({
        source: function (request, response) {
            buscarProdutoAutoComplete(request, response);
        },
        minLength: 2,
        select: function (event, ui) {
            alterarProdutoFinalSelecionado(ui.item.value, ui.item.label);
        }
    })
})

function buscarProdutoAutoComplete(produtoBuscado, resposta) {
    $.ajax('/api/produtos?busca=' + produtoBuscado.term, {
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

function alterarProdutoFinalSelecionado(idProdutoFinal, descricao) {
    $('#input-produto-final').val(idProdutoFinal);
    $('#div-produto-final-null').hide();
    $('#input-produto-final-selecionado').val(descricao);
    $('#div-produto-final-selecionado').show();
}

function selecionarProdutoFinalPorModal() {
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

function buscarProdutoModal() {
    var produtoBuscado = $('#input-produto-final').val()
    $.ajax('/api/produtos?busca='+produtoBuscado, {
        success: function (resultado) {
            gerarModalProdutosEncontrados(resultado);
        }
    })
}
 function removerProdutoFinal() {
     $('#input-produto-final').val('');
     $('#input-produto-final-selecionado').val('');

     $('#div-produto-final-selecionado').hide();
     $('#div-produto-final-null').show();
 }
