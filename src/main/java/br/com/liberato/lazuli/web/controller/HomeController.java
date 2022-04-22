package br.com.liberato.lazuli.web.controller;

import br.com.liberato.lazuli.domain.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome() {
        return "/pages/home";
    }

    @ModelAttribute("usuario")
    public Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
