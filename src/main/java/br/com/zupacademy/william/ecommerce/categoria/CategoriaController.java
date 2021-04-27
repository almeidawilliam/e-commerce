package br.com.zupacademy.william.ecommerce.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    public CategoriaRepository categoriaRepository;

    @PostMapping
    public void criar(@RequestBody @Valid CategoriaInputDto categoriaInputDto) {
        Categoria novaCategoria = categoriaInputDto.toModel(categoriaRepository);
        categoriaRepository.save(novaCategoria);
    }
}
