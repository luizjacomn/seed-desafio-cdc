package com.luizjacomn.seeddesafiocdc.validation.validator;

import com.luizjacomn.seeddesafiocdc.validation.annotation.ExistsProperty;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsPropertyValidator implements ConstraintValidator<ExistsProperty, Object> {

    private Class<?> domainClass;

    private String field;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExistsProperty constraintAnnotation) {
        this.domainClass = constraintAnnotation.domainClass();
        this.field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) return true;


        var resultList = entityManager
                            .createQuery("SELECT 1 FROM " + domainClass.getName() + " WHERE " + field + " = :value")
                            .setParameter("value", value)
                            .getResultList();

        var message = "Deve existir um registro de '" + domainClass.getSimpleName() + "' com " + field + " = " + value;

        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();

        return !resultList.isEmpty();
    }

}
