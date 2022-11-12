package br.com.liberato.lazuli.service;

import br.com.liberato.lazuli.domain.Produto;
import br.com.liberato.lazuli.domain.TipoProduto;
import br.com.liberato.lazuli.repository.ProdutoRepository;
import br.com.liberato.lazuli.repository.TipoProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * Realiza uma consulta paginada dos produtos filtrando pelo TipoProduto
     * @param tipoProduto Tipo de Produto para filtrar
     * @param paginacao Paginacao utilizada para a consulta
     * @return Produtos paginados filtrados pelo TipoProduto
     */
    public Page<Produto> buscarPorTipoProduto(TipoProduto tipoProduto, Pageable paginacao) {
        return produtoRepository.findProdutoByTipoProduto(tipoProduto, paginacao);
    }

    public Produto salvar(Produto produto) {
        produto.setDescricaoBasica(produto.getDescricaoBasica().toUpperCase());
        if (produto.getDescricaoCompleta().isEmpty()) {
            produto.setDescricaoCompleta(null);
        } else {
            produto.setDescricaoCompleta(produto.getDescricaoCompleta().toUpperCase());
        }
        Produto produtoSalvo = null;
        try {
            produtoSalvo = produtoRepository.save(produto);
            logger.info("Produto salvo com sucesso, id: " + produtoSalvo.getIdProduto());
        } catch (Exception e) {
            // TODO: Avaliar a possibilidade de retornar uma Exception para o controller
            logger.warn("Erro ao persistir produto", e);
        }
        return produtoSalvo;
    }

    public Produto editar(Produto produto) {
        if (produto == null) {
            throw new NullPointerException("Produto não pode ser null");
        }
        return salvar(produto);
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

    public Produto findById(Long idProduto) {
        return produtoRepository.findById(idProduto).orElseThrow(() -> new RuntimeException("Produto com id não" + idProduto + " encontrado"));
    }

}
