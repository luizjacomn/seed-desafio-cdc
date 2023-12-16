package com.luizjacomn.seeddesafiocdc.novacategoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriasRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNome(String nome);

}
