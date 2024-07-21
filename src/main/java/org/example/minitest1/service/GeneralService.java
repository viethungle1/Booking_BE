package org.example.minitest1.service;

public interface GeneralService<T> {
    T findById(Long id);

    T save(T t);

    void remove(Long id);
}
