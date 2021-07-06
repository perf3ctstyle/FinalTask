package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entities.Identifiable;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.Mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Identifiable> implements Dao<T> {

    private final ProxyConnection connection;
    private final Mapper<T> mapper;
    private final String tableName;

    private static final String FIND_BY_ID = "SELECT * FROM %s WHERE ID = ?";
    private static final String FIND_ALL = "SELECT * FROM %s";
    private static final String FIND_LIMITED_NUMBER_OF_ENTITIES = "SELECT * FROM %s LIMIT ?, ?";
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE ID = ?";

    private static final String MORE_ENTITIES_THAN_EXPECTED = "Expected 1 entity, but received more!";

    public AbstractDao(ProxyConnection connection, Mapper<T> mapper, String tableName) {
        this.connection = connection;
        this.mapper = mapper;
        this.tableName = tableName;
    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    protected Optional<T> executeSingleResultQuery(String query, Object... params) throws DaoException {
        List<T> entities = executeQuery(query, params);

        if (entities.size() > 1) {
            throw new DaoException(MORE_ENTITIES_THAN_EXPECTED);
        } else if (entities.size() == 1) {
            return Optional.of(entities.get(0));
        }

        return Optional.empty();
    }

    protected void executeUpdate(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    private PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        return statement;
    }

    @Override
    public void save(T entity) throws DaoException {
        if (entity.getId() == null) {
            create(entity);
        } else {
            update(entity);
        }
    }

    @Override
    public Optional<T> findById(long id) throws DaoException {
        String query = String.format(FIND_BY_ID, tableName);
        return executeSingleResultQuery(query, id);
    }

    @Override
    public List<T> findAll() throws DaoException {
        String query = String.format(FIND_ALL, tableName);
        return executeQuery(query);
    }

    public List<T> findLimitedNumberOfEntities(int offset, int numberOfEntities) throws DaoException {
        String query = String.format(FIND_LIMITED_NUMBER_OF_ENTITIES, tableName);
        return executeQuery(query, offset, numberOfEntities);
    }

    @Override
    public void deleteById(long id) throws DaoException {
        String query = String.format(DELETE_BY_ID, tableName);
        executeUpdate(query, id);
    }

    protected abstract void create(T entity) throws DaoException;

    protected abstract void update(T entity) throws DaoException;

}
