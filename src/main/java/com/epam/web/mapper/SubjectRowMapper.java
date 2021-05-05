package com.epam.web.mapper;

import com.epam.web.entities.Subject;
import com.epam.web.enums.SubjectFields;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectRowMapper implements Mapper<Subject> {
    @Override
    public Subject map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(SubjectFields.ID.toString());

        String name = resultSet.getString(SubjectFields.NAME.toString());

        return new Subject(id, name);
    }
}
