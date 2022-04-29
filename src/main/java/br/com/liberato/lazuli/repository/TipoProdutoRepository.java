package br.com.liberato.lazuli.repository;

import br.com.liberato.lazuli.domain.TipoProuto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoProdutoRepository extends JpaRepository<TipoProuto, Long> {

    Optional<TipoProuto> findByNome(String nome);
}
