package com.epam.web.mapper;

import com.epam.web.entities.Credential;
import com.epam.web.enums.CredentialFields;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CredentialRowMapper implements Mapper<Credential> {
    @Override
    public Credential map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(CredentialFields.ID.toString());

        String name = resultSet.getString(CredentialFields.NAME.toString());
        String surname = resultSet.getString(CredentialFields.SURNAME.toString());

        return new Credential(id, name, surname);
    }
}
