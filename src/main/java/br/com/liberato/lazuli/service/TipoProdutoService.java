package br.com.liberato.lazuli.service;

import br.com.liberato.lazuli.domain.TipoProduto;
import br.com.liberato.lazuli.repository.TipoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoProdutoService {

    @Autowired
    TipoProdutoRepository tipoProdutoRepository;

    public List<TipoProduto> findAll() {
        return tipoProdutoRepository.findAll();
    }

    public TipoProduto findById(Long idTipoProduto) {
        return tipoProdutoRepository.findById(idTipoProduto).orElse(null);
    }

    /**
     * Busca o tipo de produto de acordo com o nome
     * @param nomeTipoProduto nome que deve ser buscado
     * @return TipoProduto correspondente ao nome ou null caso não encontre
     */
    public TipoProduto findByNome(String nomeTipoProduto) {
        return tipoProdutoRepository.findByNome(nomeTipoProduto).orElse(null);
    }
}
