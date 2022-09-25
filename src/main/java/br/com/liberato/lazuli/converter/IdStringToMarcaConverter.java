package br.com.liberato.lazuli.converter;

import br.com.liberato.lazuli.domain.Marca;
import br.com.liberato.lazuli.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Bean para conversão de id em formato String para objeto Marca
 */
@Component
public class IdStringToMarcaConverter implements Converter<String, Marca> {

    @Autowired
    private MarcaService marcaService;

    /**
     * Recebe um ID em formato String e retorna um objeto Marca correspondente ao ID
     * @param idString ID em String da marca que deve ser convertida
     * @return Marca
     */
    @Override
    public Marca convert(String idString) {
        if (idString.isEmpty())
            return null;
        return marcaService.findById(Long.valueOf(idString));
    }
}
