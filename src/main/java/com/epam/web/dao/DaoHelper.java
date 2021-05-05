package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entities.Identifiable;
import com.epam.web.entities.User;
import com.epam.web.enums.DaoType;
import com.epam.web.exception.DaoException;

import java.sql.SQLException;

public class DaoHelper<T extends Identifiable> implements AutoCloseable {

    private final ProxyConnection connection;

    public DaoHelper(ProxyConnection connection) {
        this.connection = connection;
    }

    public AbstractDao createDao(DaoType daoType) throws DaoException {
        switch (daoType) {
            case USER:
                return new UserDao(connection);
            case FACULTY:
                return new FacultyDAO(connection);
            case SUBJECT:
                return new SubjectDAO(connection);
            default:
                throw new DaoException("Unknown DAO type!");
        }
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
