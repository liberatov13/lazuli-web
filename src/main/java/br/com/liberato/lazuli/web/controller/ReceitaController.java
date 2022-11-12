package br.com.liberato.lazuli.web.controller;

import br.com.liberato.lazuli.domain.Receita;
import br.com.liberato.lazuli.domain.Usuario;
import br.com.liberato.lazuli.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/receita")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping("/lista")
    public String getReceitas(ModelMap modelMap) {
        List<Receita> receitas = receitaService.buscarTodos();
        modelMap.addAttribute("receitas", receitas);
        return "pages/receita/lista";
    }

    @ModelAttribute("usuario")
    public Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
