package br.com.liberato.lazuli.repository;

import br.com.liberato.lazuli.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("select user from Usuario user where user.nomeUsuario = :nomeUsuario")
    Optional<Usuario> findByNomeUsuario(String nomeUsuario);

}
