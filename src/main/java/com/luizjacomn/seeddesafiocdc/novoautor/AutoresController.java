package com.luizjacomn.seeddesafiocdc.novoautor;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/autores")
// 2
public class AutoresController {

    @PersistenceContext
    private EntityManager entityManager;

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
