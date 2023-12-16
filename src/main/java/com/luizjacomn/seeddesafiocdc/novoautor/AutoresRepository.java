package com.luizjacomn.seeddesafiocdc.novoautor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutoresRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByEmail(String email);

}
