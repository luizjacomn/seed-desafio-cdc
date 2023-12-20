package com.luizjacomn.seeddesafiocdc.validation.groups;

import com.luizjacomn.seeddesafiocdc.novacompra.NovaCompraRequest;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PessoaSequenceGroupProvider implements DefaultGroupSequenceProvider<NovaCompraRequest> {

    @Override
    public List<Class<?>> getValidationGroups(NovaCompraRequest request) {
        List<Class<?>> groups = new ArrayList<>();
        groups.add(NovaCompraRequest.class);

        if (Objects.nonNull(request) && Objects.nonNull(request.getDocumento())) {
            var doc = request.getDocumento().replaceAll("\\D", "");

            if (doc.length() <= 11) {
                groups.add(PessoaFisica.class);
            } else {
                groups.add(PessoaJuridica.class);
            }
        }

        return groups;
    }

}
