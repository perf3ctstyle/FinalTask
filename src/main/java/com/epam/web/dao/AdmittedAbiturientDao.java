package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entities.AdmittedAbiturient;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.AdmittedAbiturientRowMapper;

import java.util.Optional;

public class AdmittedAbiturientDao extends AbstractDao<AdmittedAbiturient> {

    private static final String FIND_BY_USER_ID = "SELECT * FROM ADMITTED_ABITURIENT WHERE USER_ID = ?";
    private static final String CREATE_ADMITTED_ABITURIENT = "INSERT INTO ADMITTED_ABITURIENT (USER_ID, FACULTY_ID) " +
            "VALUES (?, ?)";
    private static final String UPDATE_ADMITTED_ABITURIENT = "UPDATE ADMITTED_ABITURIENT SET USER_ID = ?, " +
            "FACULTY_ID = ? WHERE ID = ?";
    private static final String DELETE_BY_USER_ID = "DELETE FROM ADMITTED_ABITURIENT WHERE USER_ID = ?";
    private static final String DELETE_BY_FACULTY_ID = "DELETE FROM ADMITTED_ABITURIENT WHERE FACULTY_ID = ?";
    private static final String TABLE = "ADMITTED_ABITURIENT";

    public AdmittedAbiturientDao(ProxyConnection connection) {
        super(connection, new AdmittedAbiturientRowMapper(), TABLE);
    }

    public Optional<AdmittedAbiturient> findByUserId(long userId) throws DaoException {
        return executeSingleResultQuery(FIND_BY_USER_ID, userId);
    }

    public void deleteByUserId(long userId) throws DaoException {
        executeUpdate(DELETE_BY_USER_ID, userId);
    }

    public void deleteByFacultyId(long facultyId) throws DaoException {
        executeUpdate(DELETE_BY_FACULTY_ID, facultyId);
    }

    @Override
    protected void create(AdmittedAbiturient admittedAbiturient) throws DaoException {
        executeUpdate(CREATE_ADMITTED_ABITURIENT, admittedAbiturient.getUserId(), admittedAbiturient.getFacultyId());
    }

    @Override
    protected void update(AdmittedAbiturient admittedAbiturient) throws DaoException {
        executeUpdate(UPDATE_ADMITTED_ABITURIENT, admittedAbiturient.getUserId(), admittedAbiturient.getFacultyId(),
                admittedAbiturient.getId());
    }
}
