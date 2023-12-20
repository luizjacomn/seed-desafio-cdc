package com.luizjacomn.seeddesafiocdc.novacompra;

import com.luizjacomn.seeddesafiocdc.validation.validator.VerificarEstadoPertenceAoPaisValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class ComprasController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private VerificarEstadoPertenceAoPaisValidator verificarEstadoPertenceAoPaisValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(verificarEstadoPertenceAoPaisValidator);
    }

    @PostMapping
    @Transactional
    // 1
    // 2
    public Compra salvar(@Valid @RequestBody NovaCompraRequest request) {
        var compra = request.toModel(entityManager);

        entityManager.persist(compra);

        return compra;
    }

}
