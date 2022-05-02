package br.com.liberato.lazuli.service;

import br.com.liberato.lazuli.domain.Produto;
import br.com.liberato.lazuli.domain.TipoProuto;
import br.com.liberato.lazuli.repository.ProdutoRepository;
import br.com.liberato.lazuli.repository.TipoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private TipoProdutoRepository tipoProdutoRepository;

    public List<Produto> findProdutos(String nomeTipoProduto) {
        if (nomeTipoProduto != null) {
            try {
                return findProdutosByNomeTipoProduto(nomeTipoProduto);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
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
