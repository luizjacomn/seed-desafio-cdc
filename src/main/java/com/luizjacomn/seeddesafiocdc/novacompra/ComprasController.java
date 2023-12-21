package com.luizjacomn.seeddesafiocdc.novacompra;

import com.luizjacomn.seeddesafiocdc.novocupom.CupomRepository;
import com.luizjacomn.seeddesafiocdc.validation.validator.VerificarCupomValidoValidator;
import com.luizjacomn.seeddesafiocdc.validation.validator.VerificarEstadoPertenceAoPaisValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
// 6
public class ComprasController {

    @PersistenceContext
    private EntityManager entityManager;

    // 1
    @Autowired
    private CupomRepository cupomRepository;

    // 1
    @Autowired
    private VerificarEstadoPertenceAoPaisValidator verificarEstadoPertenceAoPaisValidator;

    // 1
    @Autowired
    private VerificarCupomValidoValidator verificarCupomValidoValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(verificarEstadoPertenceAoPaisValidator, verificarCupomValidoValidator);
    }

    @PostMapping
    @Transactional
    // 1
    // 2
    public Compra salvar(@Valid @RequestBody NovaCompraRequest request) {
        var compra = request.toModel(entityManager, cupomRepository);

        entityManager.persist(compra);

        return compra;
    }

}
