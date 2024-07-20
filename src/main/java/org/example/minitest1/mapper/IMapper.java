package org.example.minitest1.mapper;

import org.mapstruct.MappingTarget;

public interface IMapper<S,D> {
    S to(D obj);

    void update(D dto, @MappingTarget S entity);
}
