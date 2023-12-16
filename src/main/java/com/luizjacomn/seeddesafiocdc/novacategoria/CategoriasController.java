package com.luizjacomn.seeddesafiocdc.novacategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
// 3
public class CategoriasController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProibeCategoriaNomeDuplicadoValidator proibeCategoriaNomeDuplicadoValidator;

    @InitBinder
    // 1
    public void init(WebDataBinder binder) {
        binder.addValidators(proibeCategoriaNomeDuplicadoValidator);
    }

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
