package br.com.liberato.lazuli.web.controller;

import br.com.liberato.lazuli.domain.*;
import br.com.liberato.lazuli.service.MarcaService;
import br.com.liberato.lazuli.service.ProdutoService;
import br.com.liberato.lazuli.service.TipoProdutoService;
import br.com.liberato.lazuli.service.UnidadeMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private TipoProdutoService tipoProdutoService;
    @Autowired
    private UnidadeMedidaService unidadeMedidaService;
    @Autowired
    private MarcaService marcaService;

    @GetMapping("/lista/tipo-produto/{nomeTipoProduto}")
    public String listarProdutosPaginadosPorTipoProduto(
            @PathVariable String nomeTipoProduto, @RequestParam(required = false) String campoOrdenacao,
            @RequestParam(required = false) String ordem, @RequestParam(required = false) Integer pagina,
            @RequestParam(required = false) Integer conteudoPorPagina, ModelMap modelMap) {

        if (campoOrdenacao == null)
            campoOrdenacao = "idProduto";
        Sort ordenacao = this.criarOrdenacao(campoOrdenacao, ordem);
        Pageable paginacao = criarPaginacao(pagina, conteudoPorPagina, ordenacao);

        TipoProduto tipoProduto = tipoProdutoService.findByNome(nomeTipoProduto);
        Page<Produto> produtosPaginados = produtoService.buscarPorTipoProduto(tipoProduto, paginacao);

        modelMap.addAttribute("produtos", produtosPaginados.toList());
        modelMap.addAttribute("paginacao", produtosPaginados);
        modelMap.addAttribute("tipoProduto", nomeTipoProduto);
        return "pages/produto/lista";
    }

    @GetMapping("/lista")
    public String redirecionarParaListagemProdutos(ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (!modelMap.isEmpty()) {
            modelMap.forEach(redirectAttributes::addFlashAttribute);
        }
        return "redirect:/produto/lista/tipo-produto/venda?pagina=0&conteudoPorPagina=10";
    }

    private Pageable criarPaginacao(Integer pagina, Integer conteudoPorPagina, Sort ordenacao) {
        Pageable paginacao;
        if (conteudoPorPagina != null && pagina != null) {
            paginacao = PageRequest.of(pagina, conteudoPorPagina, ordenacao);
        } else {
            paginacao = PageRequest.of(0, 10, ordenacao);
        }
        return paginacao;
    }

    private Sort criarOrdenacao(String campoOrdenacao, String ordem) {
        Sort sort;
        if (ordem != null && ordem.equalsIgnoreCase("ASC")) {
            sort = Sort.by(campoOrdenacao).ascending();
        } else {
            sort = Sort.by(campoOrdenacao).descending();
        }
        return sort;
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Produto produto, ModelMap modelMap) {
        produto.setStatus(true);
        return "pages/produto/cadastro";
    }

    @GetMapping("/editar/{id}")
    public String edicaoDeProduto(@PathVariable("id") Long idProduto, ModelMap modelMap) {
        modelMap.addAttribute("produto", produtoService.findById(idProduto));
        return "pages/produto/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Produto produto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "pages/produto/cadastro";
        }

        Produto produtoSalvo = produtoService.editar(produto);
        String mensagem = "Produto " + produtoSalvo.getDescricaoBasica() + " editado com sucesso";
        redirectAttributes.addFlashAttribute("mensagemSucesso", mensagem);
        return "redirect:/produto/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Produto produto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "pages/produto/cadastro";
        }

        Produto produtoSalvo = produtoService.salvar(produto);
        String mensagem = "Produto " + produtoSalvo.getDescricaoBasica() + " salvo com id " + produtoSalvo.getIdProduto();
        redirectAttributes.addFlashAttribute("mensagemSucesso", mensagem);
        return "redirect:/produto/lista";
    }

    @ModelAttribute("usuario")
    public Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @ModelAttribute("tiposProduto")
    public List<TipoProduto> getTiposProduto() {
        return tipoProdutoService.findAll();
    }

    @ModelAttribute("marcas")
    public List<Marca> getMarcas() {
        return marcaService.findMarcasAtivas();
    }

    @ModelAttribute("unidadesMedida")
    public List<UnidadeMedida> getUnidadesMedida() {
        return unidadeMedidaService.findAll();
    }
}
