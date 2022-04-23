package br.com.liberato.lazuli.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "erro", required = false) Boolean erro, ModelMap modelMap) {
        if (erro != null) {
            modelMap.addAttribute("erro", erro);
        }
        return "pages/usuario/login";
    }

}
