package com.codegym.service;

import java.util.List;

public interface IService<T> {
    List<T> findAll();

    List<T> findByName(String name);

    T findById(Long id);

    void remove(Long id);

    void save(T t);
}
