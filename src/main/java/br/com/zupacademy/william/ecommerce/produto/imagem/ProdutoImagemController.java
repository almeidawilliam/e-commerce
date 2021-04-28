package br.com.zupacademy.william.ecommerce.produto.imagem;

import br.com.zupacademy.william.ecommerce.produto.Produto;
import br.com.zupacademy.william.ecommerce.produto.ProdutoRepository;
import br.com.zupacademy.william.ecommerce.usuario.Usuario;
import br.com.zupacademy.william.ecommerce.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoImagemController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UploaderFake uploaderFake;

    @Transactional
    @PostMapping("/{id}/imagens")
    public ResponseEntity adicionaImagem(@PathVariable Long id,
                                         @Valid ProdutoImagemInputDto produtoImagemInputDto,
                                         @AuthenticationPrincipal Usuario usuarioLogado) {
        Optional<Produto> possivelProduto = produtoRepository.findById(id);

        if (possivelProduto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }

        Produto produto = possivelProduto.get();
        boolean eDonoDoProduto = usuarioLogado.eDonoDoProduto(produto);

        if (!eDonoDoProduto) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você nao é dono do produto");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não é dono do produto");
        }

        Set<String> links = uploaderFake.envia(produtoImagemInputDto.getImagens());
        produto.associarImagens(links);

        return ResponseEntity.ok().build();
    }
}
