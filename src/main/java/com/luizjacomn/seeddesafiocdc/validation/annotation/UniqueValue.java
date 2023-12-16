package com.luizjacomn.seeddesafiocdc.validation.annotation;

import com.luizjacomn.seeddesafiocdc.validation.validator.UniqueValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { UniqueValueValidator.class })
public @interface UniqueValue {

    String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String field() default "";

    Class<?> domainClass();

}
