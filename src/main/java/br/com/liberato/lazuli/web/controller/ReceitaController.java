package br.com.liberato.lazuli.web.controller;

import br.com.liberato.lazuli.domain.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/receita")
public class ReceitaController {

    @GetMapping("/lista")
    public String getReceitas() {
        return "pages/receita/lista";
    }

    @ModelAttribute("usuario")
    public Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
