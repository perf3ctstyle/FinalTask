package com.epam.web.mapper;

import com.epam.web.entities.Faculty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyRowMapper implements Mapper<Faculty> {

    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String ADMISSION_PLAN = "ADMISSION_PLAN";

    @Override
    public Faculty map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        String description = resultSet.getString(DESCRIPTION);
        int admissionPlan = resultSet.getInt(ADMISSION_PLAN);

        return new Faculty(id, name, description, admissionPlan);
    }
}
