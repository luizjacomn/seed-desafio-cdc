package com.luizjacomn.seeddesafiocdc.novacategoria;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
// 2
public class CategoriasController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    // 1
    // 2
    public Categoria salvar(@Valid @RequestBody NovaCategoriaRequest request) {
        var categoria = request.toModel();

        entityManager.persist(categoria);

        return categoria;
    }

}
