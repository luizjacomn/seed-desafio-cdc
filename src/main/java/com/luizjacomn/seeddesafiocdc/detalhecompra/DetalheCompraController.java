package com.luizjacomn.seeddesafiocdc.detalhecompra;

import com.luizjacomn.seeddesafiocdc.novacompra.Compra;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/compras/detalhe")
public class DetalheCompraController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/{id}")
    public DetalheCompraResponse buscar(@PathVariable Long id) {
        var compra = entityManager.find(Compra.class, id);

        return new DetalheCompraResponse(compra);
    }
}
