package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entities.Faculty;
import com.epam.web.entities.Identifiable;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.FacultyRowMapper;

import java.util.List;
import java.util.Optional;

public class FacultyDao extends AbstractDao<Faculty> {

    private static final String FIND_BY_NAME = "SELECT * FROM FACULTY WHERE NAME = ?";
    private static final String CREATE_FACULTY = "INSERT INTO FACULTY (NAME, DESCRIPTION, ADMISSION_PLAN) " +
            "VALUES (?, ?, ?)";
    private static final String UPDATE_FACULTY = "UPDATE FACULTY SET NAME = ?, DESCRIPTION = ?, " +
            "ADMISSION_PLAN = ? WHERE ID = ?";
    private static final String TABLE = "FACULTY";

    public FacultyDao(ProxyConnection connection) {
        super(connection, new FacultyRowMapper(), TABLE);
    }

    public Optional<Faculty> findByName(String name) throws DaoException {
        return executeSingleResultQuery(FIND_BY_NAME, name);
    }

    @Override
    protected void create(Faculty faculty) throws DaoException {
        executeUpdate(CREATE_FACULTY, faculty.getName(), faculty.getDescription(), faculty.getAdmissionPlan());
    }

    @Override
    protected void update(Faculty faculty) throws DaoException {
        executeUpdate(UPDATE_FACULTY,
                faculty.getName(),
                faculty.getDescription(),
                faculty.getAdmissionPlan(),
                faculty.getId());
    }
}
