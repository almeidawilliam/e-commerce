package br.com.zupacademy.william.ecommerce.pagamento;

public enum StatusPagamento {
    SUCESSO(true, "SUCESSO", "1"),
    FALHA(false, "FALHA", "0");

    private final boolean sucesso;
    private final String pagSeguro;
    private final String paypal;

    StatusPagamento(boolean sucesso, String pagSeguro, String paypal) {
        this.sucesso = sucesso;
        this.pagSeguro = pagSeguro;
        this.paypal = paypal;
    }

    public boolean isSucesso() {
        return this.sucesso;
    }

    public static StatusPagamento getEnumByString(String response) {
        for (StatusPagamento statusPagamento : StatusPagamento.values()) {
            if (statusPagamento.pagSeguro.equals(response) || statusPagamento.paypal.equals(response)) {
                return statusPagamento;
            }
        }
        return null;
    }
}

