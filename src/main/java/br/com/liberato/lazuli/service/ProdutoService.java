package br.com.liberato.lazuli.service;

import br.com.liberato.lazuli.domain.Produto;
import br.com.liberato.lazuli.domain.TipoProduto;
import br.com.liberato.lazuli.repository.ProdutoRepository;
import br.com.liberato.lazuli.repository.TipoProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProdutoService {

    Logger logger = LoggerFactory.getLogger(ProdutoService.class);

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private TipoProdutoRepository tipoProdutoRepository;

    public Page<Produto> findProdutos(String nomeTipoProduto, String campoOrdenacao, String ordem, Integer pagina,
                                     Integer quantidadeConteudoPagina) {
        // Ordenação padrão
        Sort sort = Sort.by("idProduto").ascending();
        // Paginação padrão
        Pageable pageable = Pageable.unpaged();


        if (campoOrdenacao != null) {
            Sort.Direction direction = Sort.Direction.fromString(ordem == null ? "asc" : ordem);
            sort = Sort.by(direction, campoOrdenacao);
        }

        if (quantidadeConteudoPagina != null && pagina != null)
            pageable = PageRequest.of(pagina, quantidadeConteudoPagina, sort);

        if (nomeTipoProduto != null) {
            try {
                return findProdutosByNomeTipoProduto(nomeTipoProduto, pageable);
            } catch (EntityNotFoundException e) {
                logger.warn("Não foi possível obter produtos com Tipos de Produto \"{}\"", nomeTipoProduto, e);
                return Page.empty();
            }
        }
        return produtoRepository.findAll(pageable);
    }

    private List<Produto> findProdutosByNomeTipoProduto(String nomeTipoProduto) throws EntityNotFoundException {
        TipoProduto tipo = tipoProdutoRepository.findByNome(nomeTipoProduto)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de produto com descrição \"" + nomeTipoProduto + "\" não encontrado."));
        return produtoRepository.findProdutoByTipoProduto(tipo);
    }

    private Page<Produto> findProdutosByNomeTipoProduto(String nomeTipoProduto, Pageable pageable) throws EntityNotFoundException {
        TipoProduto tipo = tipoProdutoRepository.findByNome(nomeTipoProduto)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de produto com descrição \"" + nomeTipoProduto + "\" não encontrado."));
        return produtoRepository.findProdutoByTipoProduto(tipo, pageable);
    }

}
