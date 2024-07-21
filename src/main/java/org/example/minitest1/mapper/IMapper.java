package org.example.minitest1.mapper;

import org.mapstruct.MappingTarget;


public interface IMapper<S,D> {
    S to(D obj);

    void mapping(D dto, @MappingTarget S entity);
}
