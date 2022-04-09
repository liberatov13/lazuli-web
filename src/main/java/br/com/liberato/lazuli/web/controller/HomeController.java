package br.com.liberato.lazuli.web.controller;

import br.com.liberato.lazuli.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome(ModelMap modelMap) {
        return "/pages/home";
    }

    // TODO: Retornar usuário logado no HomeController
    @ModelAttribute
    public Usuario getUsuarioLogado() {
        return null;
    }

}
