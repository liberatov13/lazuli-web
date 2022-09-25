package br.com.liberato.lazuli.converter;

import br.com.liberato.lazuli.domain.TipoProduto;
import br.com.liberato.lazuli.service.TipoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Bean para conversão de id em formato String para objeto TipoProduto
 */
@Component
public class IdStringToTipoProdutoConverter implements Converter<String, TipoProduto> {

    @Autowired
    private TipoProdutoService tipoProdutoService;

    /**
     * Recebe um ID em formato String e retorna um objeto TipoProduto correspondente ao ID
     * @param idString ID em String do tipo de produto que deve ser convertida
     * @return TipoProduto
     */
    @Override
    public TipoProduto convert(String idString) {
        if (idString.isEmpty())
            return null;
        Long idTipoProduto = Long.valueOf(idString);
        return tipoProdutoService.findById(idTipoProduto);
    }
}
