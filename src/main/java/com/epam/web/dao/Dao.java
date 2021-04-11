package com.epam.web.dao;

import com.epam.web.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> findById(long id) throws DaoException;

    List<T> findAll() throws DaoException;

    void deleteById(long id) throws DaoException;

    void save(T entity) throws DaoException;
}
