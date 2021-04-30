package br.com.zupacademy.william.ecommerce.venda;

import br.com.zupacademy.william.ecommerce.produto.Produto;
import br.com.zupacademy.william.ecommerce.produto.ProdutoRepository;
import br.com.zupacademy.william.ecommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendaRepository vendaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity vender(@AuthenticationPrincipal Usuario usuarioLogado,
                                 @RequestBody @Valid VendaRequest vendaRequest) {
        Produto produto = produtoRepository.findById(vendaRequest.getIdProduto())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Não foi encontrado um produto com id %d", vendaRequest.getIdProduto())));

        if (!produto.possuiQuantidadeEmEstoque(vendaRequest.getQuantidade())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Não há estoque suficiente");
        }

        produto.abateDoEstoque(vendaRequest.getQuantidade());

        Venda venda = vendaRequest.toModel(produto, usuarioLogado);
        Venda novaVenda = vendaRepository.save(venda);

        enviarEmailDeVenda(novaVenda);

        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).body(venda.getUrlDeRetorno());
    }

    private void enviarEmailDeVenda(Venda venda) {
        EmailVenda emailVenda = new EmailVenda(venda);
        publisher.publishEvent(emailVenda);
    }
}
