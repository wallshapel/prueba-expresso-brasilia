package com.legato.task.components;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Mapper {

    private final ModelMapper modelMapper;

    public <D, T> D toDto(T entity, Class<D> dtoClass) { // <D, T> D Significa que recibe un DTO cualquiera y una Entidad cualquiera y devuelve un DTO
        return modelMapper.map(entity, dtoClass);
    }

    public <D, T> T toEntity(D dto, Class<T> entityClass) { // <D, T> T Significa que recibe un DTO cualquiera y una Entidad cualquiera y devuelve una Entidad
        return modelMapper.map(dto, entityClass);
    }

}
