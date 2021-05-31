package com.epam.web.dao;

import com.epam.web.entities.Identifiable;
import com.epam.web.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * This is an interface that represents the Data Access Object(DAO) pattern and provides basic operations
 * for interactions with a database.
 *
 * @param <T> must implement {@link Identifiable} interface
 * @author Nikita Torop
 */
public interface Dao<T extends Identifiable> {

    /**
     * Returns an Optional object containing an {@link Identifiable} object that is received from a database
     * or an empty Optional object if nothing has been received from a database.
     * @param id - the {@link Identifiable} object's id that is to be received from a database.
     * @return Optional of an {@link Identifiable} object
     * @throws DaoException if more than 1 entity is received from a database or
     * due to {@link java.sql.PreparedStatement} behaviour
     */
    Optional<T> findById(long id) throws DaoException;

    /**
     * Returns a list of {@link Identifiable} objects that is received from a database
     * or an empty list if nothing has been received from a database.
     * @return List of {@link Identifiable} objects
     * @throws DaoException due to {@link java.sql.PreparedStatement} behaviour
     */
    List<T> findAll() throws DaoException;

    /**
     * Deletes an {@link Identifiable} object from a database by it's id.
     * @param id - the {@link Identifiable} object's id that is to be deleted from a database.
     * @throws DaoException due to {@link java.sql.PreparedStatement} behaviour
     */
    void deleteById(long id) throws DaoException;

    /**
     * Either creates or updates an {@link Identifiable} object in the database, depending on whether
     * it already exists there
     * @param entity - the object to be created or updated
     * @throws DaoException due to {@link java.sql.PreparedStatement} behaviour
     */
    void save(T entity) throws DaoException;
}
