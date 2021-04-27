package br.com.zupacademy.william.ecommerce.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioCadastroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public void criar(@RequestBody @Valid UsuarioInputDto usuarioInputDto) {
        Usuario novoUsuario = usuarioInputDto.toModel();
        usuarioRepository.save(novoUsuario);
    }
}