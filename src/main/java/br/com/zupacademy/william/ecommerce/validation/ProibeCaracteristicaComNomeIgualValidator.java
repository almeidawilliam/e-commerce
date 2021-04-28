package br.com.zupacademy.william.ecommerce.validation;

import br.com.zupacademy.william.ecommerce.produto.ProdutoInputDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProibeCaracteristicaComNomeIgualValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ProdutoInputDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        ProdutoInputDto request = (ProdutoInputDto) target;
        boolean temCaracteristicasRepetidas = request.temCaracteristicasRepetidas();

        if (temCaracteristicasRepetidas) {
            errors.rejectValue("caracteristicas", null, "Características repetidas não são aceitas");
        }
    }
}
