package br.com.liberato.lazuli.converter;

import br.com.liberato.lazuli.domain.UnidadeMedida;
import br.com.liberato.lazuli.service.UnidadeMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Bean para conversão de id em formato String para objeto UnidadeMedida
 */
@Component
public class IdStringToUnidadeMedidaConverter implements Converter<String, UnidadeMedida> {

    @Autowired
    private UnidadeMedidaService unidadeMedidaService;

    /**
     * Recebe um ID em formato String e retorna um objeto UnidadeMedida correspondente ao ID
     * @param idString ID em String da unidade de medida que deve ser convertida
     * @return UnidadeMedida
     */
    @Override
    public UnidadeMedida convert(String idString) {
        if (idString.isEmpty())
            return null;
        Long idUnidadeMedida = Long.valueOf(idString);
        return unidadeMedidaService.findById(idUnidadeMedida);
    }
}
