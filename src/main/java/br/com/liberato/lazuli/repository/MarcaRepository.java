package br.com.liberato.lazuli.repository;

import br.com.liberato.lazuli.domain.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    @Override
    List<Marca> findAll();

    List<Marca> findByStatus(Boolean status);
}
