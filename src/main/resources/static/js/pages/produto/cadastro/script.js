function desabilitaCamposDeAcordoTipoProduto() {
    let tipoProdutoSelecionado = $('#dropdown-tipo-produto').find(':selected').text()
    switch (tipoProdutoSelecionado) {
        case 'VENDA':
            $('#dropdown-marca').prop('disabled', true)
            $('#dropdown-marca').val(null)
            $('#input-codigo-barras').prop('disabled', true)
            $('#input-codigo-barras').val(null)
            break
        case 'INGREDIENTE':
            $('#dropdown-marca').prop('disabled', false)
            $('#input-codigo-barras').prop('disabled', false)
            break
        default:
            $('#dropdown-marca').prop('disabled', false)
            $('#input-codigo-barras').prop('disabled', false)
            break
    }
}
