package br.com.liberato.lazuli.resource;

import br.com.liberato.lazuli.domain.Produto;
import br.com.liberato.lazuli.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> pesquisarProduto(@RequestParam(value = "busca") String termoBusca) {
        try {
            Long idProduto = Long.parseLong(termoBusca);
            Produto produto = produtoService.findById(idProduto);
            List<Produto> produtos = Collections.singletonList(produto);
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        } catch (NumberFormatException e) {
            List<Produto> produtos = produtoService.buscarPorDescricaoBasicaParcial(termoBusca);
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        }
    }
}
