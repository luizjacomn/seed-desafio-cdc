package com.luizjacomn.seeddesafiocdc.novoautor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/autores")
// 3
public class AutoresController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProibeAutorEmailDuplicadoValidator proibeAutorEmailDuplicadoValidator;

    @InitBinder
    // 1
    public void init(WebDataBinder binder) {
        binder.addValidators(proibeAutorEmailDuplicadoValidator);
    }

    @PostMapping
    @Transactional
    // 1
    // 2
    public Autor salvar(@Valid @RequestBody NovoAutorRequest request) {
        var autor = request.toModel();

        entityManager.persist(autor);

        return autor;
    }

}
