package br.com.zupacademy.william.ecommerce.pagamento;

import br.com.zupacademy.william.ecommerce.venda.Venda;
import br.com.zupacademy.william.ecommerce.venda.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class PagamentoController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private VendaRepository vendaRepository;

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public void retornoPagSeguro(@PathVariable(name = "id") Long idVenda,
                                 @RequestBody @Valid PagamentoRequest pagamentoRequest) {
        Venda venda = vendaRepository.findById(idVenda)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Não foi encontrada uma venda com id %d", idVenda))
                );

        Pagamento novoPagamento = pagamentoRequest.toModel(venda);
        venda.adicionarTentativaPagamento(novoPagamento);

        if (venda.taPaga()) {
            enviarEmailPagamento(venda);
        } else {
            enviarEmailFalha(venda);
        }
    }

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public void retornoPaypal(@PathVariable(name = "id") Long idVenda,
                              @RequestBody @Valid PagamentoRequest pagamentoRequest) {
        Venda venda = vendaRepository.findById(idVenda)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Não foi encontrada uma venda com id %d", idVenda))
                );

        Pagamento novoPagamento = pagamentoRequest.toModel(venda);
        venda.adicionarTentativaPagamento(novoPagamento);

        if (venda.taPaga()) {
            enviarEmailPagamento(venda);
        } else {
            enviarEmailFalha(venda);
        }
    }


    private void enviarEmailPagamento(Venda venda) {
        EmailPagamentoRealizadoComSucesso email = new EmailPagamentoRealizadoComSucesso(venda);
        publisher.publishEvent(email);
    }

    private void enviarEmailFalha(Venda venda) {
        EmailPagamentoComFalha email = new EmailPagamentoComFalha(venda);
        publisher.publishEvent(email);
    }
}
