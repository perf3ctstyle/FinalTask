package com.epam.web.mapper;

import com.epam.web.entities.Faculty;
import com.epam.web.enums.FacultyFields;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyRowMapper implements Mapper<Faculty> {
    @Override
    public Faculty map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(FacultyFields.ID.toString());

        String name = resultSet.getString(FacultyFields.NAME.toString());
        String description = resultSet.getString(FacultyFields.DESCRIPTION.toString());

        int admissionPlan = resultSet.getInt(FacultyFields.ADMISSION_PLAN.toString());

        return new Faculty(id, name, description, admissionPlan);
    }
}
