package com.epam.web.mapper;

import com.epam.web.entities.Credential;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CredentialRowMapper implements Mapper<Credential> {

    private static final String ID = "ID";
    private static final String USER_ID = "USER_ID";
    private static final String NAME = "NAME";
    private static final String SURNAME = "SURNAME";

    @Override
    public Credential map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        Long userId = resultSet.getLong(USER_ID);

        String name = resultSet.getString(NAME);
        String surname = resultSet.getString(SURNAME);

        return new Credential(id, userId, name, surname);
    }
}
