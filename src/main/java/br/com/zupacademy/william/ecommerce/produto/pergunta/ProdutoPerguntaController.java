package br.com.zupacademy.william.ecommerce.produto.pergunta;

import br.com.zupacademy.william.ecommerce.produto.Produto;
import br.com.zupacademy.william.ecommerce.produto.ProdutoRepository;
import br.com.zupacademy.william.ecommerce.produto.pergunta.evento.EmailProdutoPerguntaEvent;
import br.com.zupacademy.william.ecommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/produtos/{id}/perguntas")
public class ProdutoPerguntaController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoPerguntaRepository produtoPerguntaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping
    public ResponseEntity criar(@AuthenticationPrincipal Usuario usuarioLogado,
                                @PathVariable Long id,
                                @RequestBody @Valid ProdutoPerguntaRequestDto produtoPerguntaRequestDto) {

        if (Objects.isNull(usuarioLogado)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "É necessário estar logado para realizar esta operação");
        }
        Optional<Produto> possivelProduto = produtoRepository.findById(id);

        if (possivelProduto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Não foi encontrado um produto com id %d", id));
        }

        Produto produto = possivelProduto.get();
        ProdutoPergunta novoProdutoPergunta = produtoPerguntaRequestDto.toModel(produto, usuarioLogado);
        EmailProdutoPerguntaEvent emailDePergunta = novoProdutoPergunta.criarEmail();

        publisher.publishEvent(emailDePergunta);

        produtoPerguntaRepository.save(novoProdutoPergunta);

        return ResponseEntity.ok().build();
    }
}
