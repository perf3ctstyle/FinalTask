package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.exception.DaoException;

public class DaoHelper implements AutoCloseable {

    private final ProxyConnection connection;

    private static final String ADMITTED_ABITURIENT_DAO = "ADMITTED_ABITURIENT_DAO";
    private static final String APPLICATION_DAO = "APPLICATION_DAO";
    private static final String CREDENTIAL_DAO = "CREDENTIAL_DAO";
    private static final String FACULTY_DAO = "FACULTY_DAO";
    private static final String REGISTER_DAO = "REGISTER_DAO";
    private static final String USER_DAO = "USER_DAO";
    private static final String UNKNOWN_DAO = "Unknown DAO type!";

    public DaoHelper(ProxyConnection connection) {
        this.connection = connection;
    }

    public AbstractDao createDao(String daoType) throws DaoException {
        switch (daoType) {
            case USER_DAO:
                return new UserDao(connection);
            case FACULTY_DAO:
                return new FacultyDao(connection);
            case APPLICATION_DAO:
                return new ApplicationDao(connection);
            case CREDENTIAL_DAO:
                return new CredentialDao(connection);
            case REGISTER_DAO:
                return new RegisterDao(connection);
            case ADMITTED_ABITURIENT_DAO:
                return new AdmittedAbiturientDao(connection);
            default:
                throw new DaoException(UNKNOWN_DAO);
        }
    }

    @Override
    public void close() {
        connection.close();
    }
}
