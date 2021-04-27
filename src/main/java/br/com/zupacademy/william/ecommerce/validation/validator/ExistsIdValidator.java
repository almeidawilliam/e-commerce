package br.com.zupacademy.william.ecommerce.validation.validator;

import br.com.zupacademy.william.ecommerce.validation.annotation.ExistsId;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {

    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ExistsId params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.nonNull(value)) {
            Query query = manager.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + "=:value");
            query.setParameter("value", value);
            Object object = manager.find(klass, value);
            return object != null;
        }

        return true;
    }
}
