package com.luizjacomn.seeddesafiocdc.novacategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeCategoriaNomeDuplicadoValidator implements Validator {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCategoriaRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;

        var categoria = (NovaCategoriaRequest) target;

        categoriasRepository.findByNome(categoria.getNome()).ifPresent(cat -> {
            errors.rejectValue("nome", "", "Já existe uma categoria com o nome informado!");
        });
    }
}
