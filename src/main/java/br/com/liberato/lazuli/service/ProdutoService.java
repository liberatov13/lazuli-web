package br.com.liberato.lazuli.service;

import br.com.liberato.lazuli.domain.Produto;
import br.com.liberato.lazuli.domain.TipoProuto;
import br.com.liberato.lazuli.repository.ProdutoRepository;
import br.com.liberato.lazuli.repository.TipoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private TipoProdutoRepository tipoProdutoRepository;

    public List<Produto> findProdutos(String tipoProuto) {
        if (tipoProuto != null) {
            TipoProuto tipo = tipoProdutoRepository.findByNome(tipoProuto).orElseThrow(() -> {
                // TODO: Incluir tratamento para Tipos de Produto não localizados
                throw new RuntimeException("Tipo de produto com descrição \"" + tipoProuto + "\" não encontrado.");
            });
            return produtoRepository.findProdutoByTipoProuto(tipo);
        }
        return produtoRepository.findAll();
    }
}
