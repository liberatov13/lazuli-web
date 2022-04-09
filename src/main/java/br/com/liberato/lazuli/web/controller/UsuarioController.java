package br.com.liberato.lazuli.web.controller;

import br.com.liberato.lazuli.domain.Usuario;
import br.com.liberato.lazuli.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

    @GetMapping("/login")
    public String login(@RequestParam(value = "erro", required = false) Boolean erro, ModelMap modelMap) {
        if (erro != null) {
            modelMap.addAttribute("erro", erro);

            // TODO: Retornar mensagem de erro na pagina ao tentar acessar
            modelMap.addAttribute("mensagem-erro", modelMap.getAttribute("mensagem-erro"));
        }
        return "/pages/usuario/login";
    }

    @GetMapping("/buscar/nomeUsuario")
    public Usuario buscarPorNomeUsuario(@RequestParam("nomeUsuario") String nomeUsuario) {
        Optional<Usuario> usuarioOptional = usuarioService.findByNomeUsuario(nomeUsuario);
        return usuarioOptional.orElseGet(Usuario::new);
    }

}
