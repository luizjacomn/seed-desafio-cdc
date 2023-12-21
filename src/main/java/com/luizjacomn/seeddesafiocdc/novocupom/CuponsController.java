package com.luizjacomn.seeddesafiocdc.novocupom;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/cupons")
// 2
public class CuponsController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    // 1
    // 2
    public Cupom salvar(@Valid @RequestBody NovoCupomRequest request) {
        var cupom = request.toModel();

        entityManager.persist(cupom);

        return cupom;
    }

}
