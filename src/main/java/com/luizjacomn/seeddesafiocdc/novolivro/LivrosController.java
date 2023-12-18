package com.luizjacomn.seeddesafiocdc.novolivro;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
// 3
public class LivrosController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    // 1
    // 2
    public Livro salvar(@Valid @RequestBody NovoLivroRequest request) {
        var livro = request.toModel(entityManager);

        entityManager.persist(livro);

        return livro;
    }

    @GetMapping
    // 1
    public List<LivroResponse> listar() {
        // 1
        return entityManager
                .createQuery("SELECT new com.luizjacomn.seeddesafiocdc.novolivro.LivroResponse(l) FROM Livro l", LivroResponse.class)
                .getResultStream()
                .collect(Collectors.toList());
    }

}
