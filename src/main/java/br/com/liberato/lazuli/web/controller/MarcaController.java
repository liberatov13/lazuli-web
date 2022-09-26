package br.com.liberato.lazuli.web.controller;

import br.com.liberato.lazuli.domain.Marca;
import br.com.liberato.lazuli.domain.Usuario;
import br.com.liberato.lazuli.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    MarcaService marcaService;

    @GetMapping("/listar")
    public String listar(ModelMap modelMap) {
        modelMap.addAttribute("marcas", marcaService.findMarcas());
        return "pages/marca/lista";
    }

    @PostMapping("/salvar")
    public String salvar(Marca marca, RedirectAttributes redirectAttributes) {
        Marca marcaSalva = marcaService.salvar(marca);
        String mensagem = "Marca " + marcaSalva.getNome() + " salva com id: " + marca.getIdMarca();
        redirectAttributes.addFlashAttribute("mensagemSucesso", mensagem);
        return "redirect:/marca/listar";
    }

    @GetMapping("/editar/{id}")
    public String edicaoMarca(@PathVariable("id") Long idMarca, ModelMap modelMap) {
        modelMap.addAttribute("marca", marcaService.findById(idMarca));
        return "pages/marca/cadastro";
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Marca marca) {
        marca.setStatus(true);
        return "pages/marca/cadastro";
    }

    @PostMapping("/editar")
    public String editar(Marca marca, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "pages/marca/cadastro";
        }
        marcaService.editar(marca);
        String mensagem = "Marca " + marca.getNome() + " editada com sucesso";
        redirectAttributes.addFlashAttribute("mensagemSucesso", mensagem);
        return "redirect:/marca/listar";
    }

    @ModelAttribute("usuario")
    public Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
