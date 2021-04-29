package br.com.zupacademy.william.ecommerce.produto.avaliacao;

import br.com.zupacademy.william.ecommerce.produto.Produto;
import br.com.zupacademy.william.ecommerce.produto.ProdutoRepository;
import br.com.zupacademy.william.ecommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos/{id}/avaliacoes")
public class ProdutoAvaliacaoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoAvaliacaoRepository produtoAvaliacaoRepository;

    @PostMapping
    public ResponseEntity criar(@PathVariable Long id,
                                @RequestBody @Valid ProdutoAvaliacaoInputDto produtoAvaliacaoInputDto,
                                @AuthenticationPrincipal Usuario usuarioLogado) {
        Optional<Produto> possivelProduto = produtoRepository.findById(id);

        if (possivelProduto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Produto de id %s não encontrado", id));
        }

        Produto produto = possivelProduto.get();
        ProdutoAvaliacao novaAvaliacao = produtoAvaliacaoInputDto.toModel(produto, usuarioLogado);
        produtoAvaliacaoRepository.save(novaAvaliacao);

        return ResponseEntity.ok().build();
    }
}
