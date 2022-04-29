package br.com.liberato.lazuli.repository;

import br.com.liberato.lazuli.domain.Produto;
import br.com.liberato.lazuli.domain.TipoProuto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findProdutoByTipoProuto(TipoProuto tipoProuto);

}
