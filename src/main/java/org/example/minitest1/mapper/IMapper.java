package org.example.minitest1.mapper;

public interface IMapper<S,D> {
    S to(D obj);

    D from(S obj);
}
