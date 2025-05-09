package com.example.gestion_produits_api.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private  final ModelMapper mapper = new ModelMapper();

    public <E, D> D toDTO(E entity, Class<D> dtoClass) {
        return mapper.map(entity, dtoClass);
    }

    public <E, D> E toEntity(D dto, Class<E> entityClass) {
        return mapper.map(dto, entityClass);
    }

}
