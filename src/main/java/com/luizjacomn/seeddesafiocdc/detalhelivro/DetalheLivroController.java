package com.luizjacomn.seeddesafiocdc.detalhelivro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/livros/detalhe")
// 2
public class DetalheLivroController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/{id}")
    // 2
    public DetalheLivroResponse buscar(@PathVariable Long id) {
        try {
            // 1
            return entityManager
                    .createQuery("SELECT new com.luizjacomn.seeddesafiocdc.detalhelivro.DetalheLivroResponse(l) FROM Livro l WHERE l.id = :id", DetalheLivroResponse.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            // 2
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
