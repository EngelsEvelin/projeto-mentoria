package com.example.login_app.repositories;

import java.util.Optional;

public interface GenericRepository<T, ID> {
    Optional<T> findById(ID id);
    T save(T entity);
    void deleteById(ID id);
}

