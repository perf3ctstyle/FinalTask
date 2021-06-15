package com.epam.web.mapper;

import com.epam.web.entities.AdmittedAbiturient;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdmittedAbiturientRowMapper implements Mapper<AdmittedAbiturient> {

    private static final String ID = "ID";
    private static final String USER_ID = "USER_ID";
    private static final String FACULTY_ID = "FACULTY_ID";

    @Override
    public AdmittedAbiturient map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        Long userId = resultSet.getLong(USER_ID);
        Long facultyId = resultSet.getLong(FACULTY_ID);

        return new AdmittedAbiturient(id, userId, facultyId);
    }
}
