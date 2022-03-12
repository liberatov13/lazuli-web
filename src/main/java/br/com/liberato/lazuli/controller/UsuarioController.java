package br.com.liberato.lazuli.controller;

import br.com.liberato.lazuli.domain.Usuario;
import br.com.liberato.lazuli.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public void buscarPorId(@PathVariable("id") Long idUsuario) {
        System.out.println("Teste");
        usuarioService.buscarPorId(idUsuario);
    }

    @GetMapping("/buscar/nomeUsuario")
    public Usuario buscarPorNomeUsuario(@RequestParam("nomeUsuario") String nomeUsuario) {
        Optional<Usuario> usuarioOptional = usuarioService.buscarPorNomeUsuario(nomeUsuario);
        return usuarioOptional.orElseGet(Usuario::new);
    }

}
