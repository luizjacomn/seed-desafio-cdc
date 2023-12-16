package com.luizjacomn.seeddesafiocdc.validation.validator;

import com.luizjacomn.seeddesafiocdc.validation.annotation.UniqueValue;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String field;

    private Class<?> domainClass;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.domainClass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        var resultList = entityManager
                            .createQuery("SELECT 1 FROM " + domainClass.getName() + " WHERE " + field + " = :value")
                            .setParameter("value", value)
                            .getResultList();

        var message = "Já existe um registro com o " + field + " = '" + value + "' no cadastro de '" + domainClass.getSimpleName() + "'";

        Assert.isTrue(resultList.size() <= 1, message);

        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();

        return resultList.isEmpty();
    }

}
