package br.com.liberato.lazuli.web.controller;

import br.com.liberato.lazuli.domain.TipoProduto;
import br.com.liberato.lazuli.domain.Usuario;
import br.com.liberato.lazuli.service.TipoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/tipo-produto")
public class TipoProdutoController {

    @Autowired
    TipoProdutoService tipoProdutoService;

    @GetMapping("/listar")
    public String listar(ModelMap modelMap) {
        List<TipoProduto> tiposProduto = tipoProdutoService.findAll();
        modelMap.addAttribute("tiposProduto", tiposProduto);
        return "pages/tipo-produto/lista";
    }

    @GetMapping("/cadastrar")
    public String cadastrar(TipoProduto tipoProduto) {
        return "pages/tipo-produto/cadastro";
    }

    @PostMapping("/editar")
    public String editar(TipoProduto tipoProduto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "pages/tipo-produto/cadastro";
        }
        TipoProduto tipoProdutoSalvo = tipoProdutoService.salvar(tipoProduto);
        String mensagem = "Tipo de produto " + tipoProdutoSalvo.getNome() + " editado com sucesso";
        redirectAttributes.addFlashAttribute("mensagemSucesso", mensagem);
        return "redirect:/tipo-produto/listar";
    }

    @GetMapping("/editar/{id}")
    public String edicaoTipoProduto(@PathVariable("id") Long idTipoProduto, ModelMap modelMap) {
        TipoProduto tipoProduto = tipoProdutoService.findById(idTipoProduto);
        modelMap.addAttribute("tipoProduto", tipoProduto);
        return "pages/tipo-produto/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(TipoProduto tipoProduto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "pages/tipo-produto/cadastro";
        }
        TipoProduto tipoProdutoSalvo = tipoProdutoService.salvar(tipoProduto);
        String mensagem = "Tipo de produto " + tipoProdutoSalvo.getNome() + " salvo com código " + tipoProdutoSalvo.getIdTipoProduto();
        redirectAttributes.addFlashAttribute("mensagemSucesso", mensagem);
        return "redirect:/tipo-produto/listar";
    }

    @ModelAttribute("usuario")
    public Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
