package com.luizjacomn.seeddesafiocdc.validation.validator;

import com.luizjacomn.seeddesafiocdc.novacompra.NovaCompraRequest;
import com.luizjacomn.seeddesafiocdc.novoestado.Estado;
import com.luizjacomn.seeddesafiocdc.novopais.Pais;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class VerificarEstadoPertenceAoPaisValidator implements Validator {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;

        var novaCompra = (NovaCompraRequest) target;

        var estado = entityManager.find(Estado.class, novaCompra.getEstadoId());
        var pais = entityManager.find(Pais.class, novaCompra.getPaisId());

        if (!estado.getPais().equals(pais)) {
            errors.rejectValue("estadoId", "", "O estado não pertence ao país informado!");
        }
    }

}
