package br.com.zupacademy.william.ecommerce.exception;

import java.util.List;

public class ErrorBody {

    private List<FieldError> fieldErrors;

    public ErrorBody(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}

class FieldError {
    private final String campo;
    private final String mensagem;

    public FieldError(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
