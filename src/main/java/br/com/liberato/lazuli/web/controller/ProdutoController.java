package br.com.liberato.lazuli.web.controller;

import br.com.liberato.lazuli.domain.Produto;
import br.com.liberato.lazuli.domain.Usuario;
import br.com.liberato.lazuli.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/lista")
    public String listaProdutos(@RequestParam(required = false) String tipoProduto,
                                ModelMap modelMap) {
        List<Produto> produtos = produtoService.findProdutos(tipoProduto);
        modelMap.addAttribute("produtos", produtos);
        if (tipoProduto != null) {
            modelMap.addAttribute("tipoProduto", tipoProduto);
        }
        return "pages/produto/lista";
    }

    @ModelAttribute("usuario")
    public Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
