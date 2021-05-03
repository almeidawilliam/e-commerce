package br.com.zupacademy.william.ecommerce.pagamento;

import br.com.zupacademy.william.ecommerce.email.Email;
import br.com.zupacademy.william.ecommerce.venda.Venda;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmailPagamentoComFalha implements Email {

    private static final String remetente = "ecommerce@ecomerce.com";

    private String titulo = "%s, seu pagamento não pode ser realizado com sucesso.\nProduto: %s\nQuantidade: %s\nValor: %s\nData da recusa: %s";
    private String destinatario;

    public EmailPagamentoComFalha(Venda venda) {
        this.titulo = String.format(titulo, venda.getEmailDoComprador(), venda.getProduto().getNome(), venda.getQuantidade(), venda.getValor(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        this.destinatario = venda.getEmailDoComprador();
    }

    @Override
    public String getTitulo() {
        return this.titulo;
    }

    @Override
    public String getRemetente() {
        return this.remetente;
    }

    @Override
    public String getDestinatario() {
        return this.destinatario;
    }
}
