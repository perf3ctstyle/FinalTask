package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entities.Identifiable;
import com.epam.web.entities.Register;
import com.epam.web.enums.DaoType;
import com.epam.web.exception.DaoException;

public class DaoHelper implements AutoCloseable {

    private final ProxyConnection connection;

    private static final String UNKNOWN_DAO = "Unknown DAO type!";

    public DaoHelper(ProxyConnection connection) {
        this.connection = connection;
    }

    public AbstractDao createDao(DaoType daoType) throws DaoException {
        switch (daoType) {
            case USER:
                return new UserDao(connection);
            case FACULTY:
                return new FacultyDao(connection);
            case APPLICATION:
                return new ApplicationDao(connection);
            case CREDENTIAL:
                return new CredentialDao(connection);
            case REGISTER:
                return new RegisterDao(connection);
            default:
                throw new DaoException(UNKNOWN_DAO);
        }
    }

    @Override
    public void close() {
        connection.close();
    }
}
