package br.com.liberato.lazuli.service;

import br.com.liberato.lazuli.domain.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Optional<Usuario> buscarPorId(Long idUsuario);

    Optional<Usuario> findByNomeUsuario(String nomeUsuario);
}
