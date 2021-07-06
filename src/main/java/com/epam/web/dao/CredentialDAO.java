package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entities.Credential;
import com.epam.web.entities.Faculty;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.CredentialRowMapper;

import java.util.List;
import java.util.Optional;

public class CredentialDao extends AbstractDao<Credential> {

    private static final String FIND_BY_USER_ID = "SELECT * FROM USER_CREDENTIAL WHERE USER_ID = ?";
    private static final String FIND_BY_NAME_AND_SURNAME = "SELECT * FROM USER_CREDENTIAL WHERE NAME = ? AND SURNAME = ?";
    private static final String CREATE_CREDENTIAL = "INSERT INTO USER_CREDENTIAL (USER_ID, NAME, SURNAME) " +
            "VALUES (?, ?, ?)";
    private static final String UPDATE_CREDENTIAL = "UPDATE USER_CREDENTIAL SET NAME = ?, SURNAME = ?, " +
            "WHERE ID = ?";
    private static final String DELETE_BY_USER_ID = "DELETE FROM USER_CREDENTIAL WHERE USER_ID = ?";
    private static final String TABLE = "USER_CREDENTIAL";

    public CredentialDao(ProxyConnection connection) {
        super(connection, new CredentialRowMapper(), TABLE);
    }

    public Optional<Credential> findByUserId(long id) throws DaoException {
        return executeSingleResultQuery(FIND_BY_USER_ID, id);
    }

    public void deleteByUserId(long userId) throws DaoException {
        executeUpdate(DELETE_BY_USER_ID, userId);
    }

    public Optional<Credential> findByNameAndSurname(String name, String surname) throws DaoException {
        return executeSingleResultQuery(FIND_BY_NAME_AND_SURNAME, name, surname);
    }

    @Override
    protected void create(Credential credential) throws DaoException {
        executeUpdate(CREATE_CREDENTIAL, credential.getUserId(), credential.getName(), credential.getSurname());
    }

    @Override
    protected void update(Credential credential) throws DaoException {
        executeUpdate(UPDATE_CREDENTIAL, credential.getName(), credential.getSurname(), credential.getId());
    }
}
