package com.luizjacomn.seeddesafiocdc.validation.annotation;

import com.luizjacomn.seeddesafiocdc.validation.validator.ExistsPropertyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ExistsPropertyValidator.class })
public @interface ExistsProperty {

    String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    Class<?> domainClass();

    String field() default "";

}
