package com.luizjacomn.seeddesafiocdc.validation.validator;

import com.luizjacomn.seeddesafiocdc.validation.annotation.ExistsId;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.stream.Stream;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {

    private Class<?> domainClass;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExistsId constraintAnnotation) {
        this.domainClass = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) return true;


        var idField = Stream.of(domainClass.getDeclaredFields())
                            .filter(field -> field.isAnnotationPresent(Id.class))
                            .findAny()
                            .orElseThrow();

        var resultList = entityManager
                            .createQuery("SELECT 1 FROM " + domainClass.getName() + " WHERE " + idField.getName() + " = :value")
                            .setParameter("value", value)
                            .getResultList();

        var message = "Deve existir um registro de '" + domainClass.getSimpleName() + "' com " + idField.getName() + " = " + value;

        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();

        return !resultList.isEmpty();
    }

}
