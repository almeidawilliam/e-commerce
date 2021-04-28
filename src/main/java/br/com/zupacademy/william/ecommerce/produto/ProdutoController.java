package br.com.zupacademy.william.ecommerce.produto;

import br.com.zupacademy.william.ecommerce.categoria.Categoria;
import br.com.zupacademy.william.ecommerce.categoria.CategoriaRepository;
import br.com.zupacademy.william.ecommerce.usuario.Usuario;
import br.com.zupacademy.william.ecommerce.validation.ProibeCaracteristicaComNomeIgualValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
    }

    @PostMapping
    public ResponseEntity criar(@RequestBody @Valid ProdutoInputDto produtoInputDto,
                                @AuthenticationPrincipal Usuario usuarioLogado) {
        Optional<Categoria> possivelCategoria = categoriaRepository.findById(produtoInputDto.getIdCategoria());

        if (possivelCategoria.isEmpty()) {
            throw new EntityNotFoundException(String.format("Não existe uma categoria com id %d", produtoInputDto.getIdCategoria()));
        }

        Produto novoProduto = produtoInputDto.toModel(possivelCategoria.get(), usuarioLogado);
        produtoRepository.save(novoProduto);
        return ResponseEntity.ok().build();
    }
}
