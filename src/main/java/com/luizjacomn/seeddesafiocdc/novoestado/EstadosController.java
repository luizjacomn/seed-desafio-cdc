package com.luizjacomn.seeddesafiocdc.novoestado;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/estados")
public class EstadosController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    // 1
    // 2
    public Estado salvar(@Valid @RequestBody NovoEstadoRequest request) {
        var estado = request.toModel(entityManager);

        entityManager.persist(estado);

        return estado;
    }

}
