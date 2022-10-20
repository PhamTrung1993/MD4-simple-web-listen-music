package com.codegym.repository;

import java.util.List;

public interface IRepository<T> {
    List<T> findAll();

    List<T> findByName(String name);

    T findById(Long id);

    void remove(Long id);

    void save(T t);
}
