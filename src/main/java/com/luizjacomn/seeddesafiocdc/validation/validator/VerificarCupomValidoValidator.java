package com.luizjacomn.seeddesafiocdc.validation.validator;

import com.luizjacomn.seeddesafiocdc.novacompra.NovaCompraRequest;
import com.luizjacomn.seeddesafiocdc.novocupom.CupomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class VerificarCupomValidoValidator implements Validator {

    @Autowired
    private CupomRepository cupomRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;

        var novaCompra = (NovaCompraRequest) target;

        novaCompra.getCodigoCupom().ifPresent(codigoCupom -> {
            var cupom = cupomRepository.findByCodigo(codigoCupom).orElseThrow();
            if (!cupom.valido()) {
                errors.rejectValue("codigoCupom", "", "O cupom informado é inválido!");
            }
        });
    }

}
