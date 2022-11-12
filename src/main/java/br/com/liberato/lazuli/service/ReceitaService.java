package br.com.liberato.lazuli.service;

import br.com.liberato.lazuli.domain.Receita;
import br.com.liberato.lazuli.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    public List<Receita> buscarTodos() {
        return receitaRepository.findAll();
    }
}
