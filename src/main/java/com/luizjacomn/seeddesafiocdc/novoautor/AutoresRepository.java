package com.luizjacomn.seeddesafiocdc.novoautor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutoresRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByEmail(String email);

}
