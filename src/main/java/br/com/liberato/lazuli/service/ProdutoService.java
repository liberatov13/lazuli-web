package br.com.liberato.lazuli.service;

import br.com.liberato.lazuli.domain.Produto;
import br.com.liberato.lazuli.domain.TipoProuto;
import br.com.liberato.lazuli.repository.ProdutoRepository;
import br.com.liberato.lazuli.repository.TipoProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@Service
public class ProdutoService {

    Logger logger = LoggerFactory.getLogger(ProdutoService.class);

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private TipoProdutoRepository tipoProdutoRepository;

    public List<Produto> findProdutos(String nomeTipoProduto) {
        if (nomeTipoProduto != null) {
            try {
                return findProdutosByNomeTipoProduto(nomeTipoProduto);
            } catch (EntityNotFoundException e) {
                logger.warn("Não foi possível obter produtos com Tipos de Produto \"{}\"", nomeTipoProduto, e);
                return Collections.emptyList();
            }
        }
        return produtoRepository.findAll();
    }

    private List<Produto> findProdutosByNomeTipoProduto(String nomeTipoProduto) throws EntityNotFoundException {
        TipoProuto tipo = tipoProdutoRepository.findByNome(nomeTipoProduto)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de produto com descrição \"" + nomeTipoProduto + "\" não encontrado."));
        return produtoRepository.findProdutoByTipoProuto(tipo);
    }
}
