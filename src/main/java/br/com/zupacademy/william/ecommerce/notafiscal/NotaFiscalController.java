package br.com.zupacademy.william.ecommerce.notafiscal;

import br.com.zupacademy.william.ecommerce.usuario.Usuario;
import br.com.zupacademy.william.ecommerce.venda.Venda;
import br.com.zupacademy.william.ecommerce.venda.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/venda/{id}/nota-fiscal")
public class NotaFiscalController {

    @Autowired
    private VendaRepository vendaRepository;

    @PostMapping
    public ResponseEntity emitirNotaFiscal(@PathVariable Long id) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Não foi encontrada uma venda com id %d", id))
                );

        Usuario comprador = venda.getComprador();

        return ResponseEntity.ok().build();
    }
}
