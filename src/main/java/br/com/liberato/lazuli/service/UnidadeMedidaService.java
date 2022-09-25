package br.com.liberato.lazuli.service;

import br.com.liberato.lazuli.domain.UnidadeMedida;
import br.com.liberato.lazuli.repository.UnidadeMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeMedidaService {

    @Autowired
    private UnidadeMedidaRepository unidadeMedidaRepository;

    public List<UnidadeMedida> findAll() {
        return unidadeMedidaRepository.findAll();
    }

    public UnidadeMedida findById(Long idUnidadeMedida) {
        return unidadeMedidaRepository.findById(idUnidadeMedida).orElse(null);
    }
}
