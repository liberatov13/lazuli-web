package br.com.liberato.lazuli.web.controller;

import br.com.liberato.lazuli.domain.Produto;
import br.com.liberato.lazuli.domain.Usuario;
import br.com.liberato.lazuli.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
                                @RequestParam(required = false) String campoOrdenacao,
                                @RequestParam(required = false) String ordem,
                                @RequestParam(required = false) Integer pagina,
                                @RequestParam(required = false) Integer conteudoPorPagina,
                                ModelMap modelMap) {
        Page<Produto> produtosPaginados = produtoService.findProdutos(tipoProduto, campoOrdenacao, ordem, pagina, conteudoPorPagina);

        modelMap.addAttribute("produtos", produtosPaginados.toList());
        modelMap.addAttribute("paginacao", produtosPaginados);
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
