package br.com.liberato.lazuli.repository;

import br.com.liberato.lazuli.domain.Produto;
import br.com.liberato.lazuli.domain.TipoProduto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findAll();

    Page<Produto> findAll(Pageable pageable);

    List<Produto> findProdutoByTipoProduto(TipoProduto tipoProduto);

    Page<Produto> findProdutoByTipoProduto(TipoProduto tipoProduto, Pageable pageable);

    List<Produto> findAllByDescricaoBasicaContainingIgnoreCase(String descricaoBasica);
}
