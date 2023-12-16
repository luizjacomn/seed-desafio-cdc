package com.luizjacomn.seeddesafiocdc.novoautor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeAutorEmailDuplicadoValidator implements Validator {

    @Autowired
    private AutoresRepository autoresRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoAutorRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        var novoAutor = (NovoAutorRequest) target;

        autoresRepository.findByEmail(novoAutor.getEmail()).ifPresent(autor ->
            errors.rejectValue("email", "", "Já existe um autor com o e-mail informado!")
        );
    }

}
