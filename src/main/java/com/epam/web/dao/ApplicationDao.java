package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entities.Application;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.ApplicationRowMapper;
import com.epam.web.mapper.FacultyRowMapper;

import java.util.List;
import java.util.Optional;

public class ApplicationDao extends AbstractDao<Application> {

    private static final String FIND_BY_USER_ID = "SELECT * FROM USER_APPLICATION WHERE USER_ID = ?";
    private static final String CREATE_APPLICATION = "INSERT INTO USER_APPLICATION (USER_ID, FACULTY_ID, " +
            "CERTIFICATE_SCORE, FIRST_SUBJECT_SCORE, SECOND_SUBJECT_SCORE, THIRD_SUBJECT_SCORE) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_APPLICATION = "UPDATE USER_APPLICATION SET FACULTY_ID = ?, " +
            "CERTIFICATE_SCORE = ?, FIRST_SUBJECT_SCORE = ?, SECOND_SUBJECT_SCORE = ?, THIRD_SUBJECT_SCORE = ?" +
            " WHERE ID = ?";
    private static final String UPDATE_BY_USER_ID = "UPDATE USER_APPLICATION SET FACULTY_ID = ?, " +
            "CERTIFICATE_SCORE = ?, FIRST_SUBJECT_SCORE = ?, SECOND_SUBJECT_SCORE = ?, THIRD_SUBJECT_SCORE = ?" +
            " WHERE USER_ID = ?";
    private static final String DELETE_BY_USER_ID = "DELETE FROM USER_APPLICATION WHERE USER_ID = ?";
    private static final String TABLE = "USER_APPLICATION";

    public ApplicationDao(ProxyConnection connection) {
        super(connection, new ApplicationRowMapper(), TABLE);
    }

    public Optional<Application> findByUserId(long userId) throws DaoException {
        return executeSingleResultQuery(FIND_BY_USER_ID, userId);
    }

    public void deleteByUserId(long userId) throws DaoException {
        executeUpdate(DELETE_BY_USER_ID, userId);
    }

    public void updateByUserId(Application application) throws DaoException {
        List<Integer> subjectsScores = application.getSubjectsScores();
        executeUpdate(UPDATE_BY_USER_ID,
                application.getFacultyId(),
                application.getCertificateScore(),
                subjectsScores.get(0),
                subjectsScores.get(1),
                subjectsScores.get(2),
                application.getUserId());
    }

    @Override
    protected void create(Application application) throws DaoException {
        List<Integer> subjectsScores = application.getSubjectsScores();
        executeUpdate(CREATE_APPLICATION,
                application.getUserId(),
                application.getFacultyId(),
                application.getCertificateScore(),
                subjectsScores.get(0),
                subjectsScores.get(1),
                subjectsScores.get(2));
    }

    @Override
    protected void update(Application application) throws DaoException {
        List<Integer> subjectsScores = application.getSubjectsScores();
        executeUpdate(UPDATE_APPLICATION,
                application.getFacultyId(),
                application.getCertificateScore(),
                subjectsScores.get(0),
                subjectsScores.get(1),
                subjectsScores.get(2),
                application.getId());
    }
}
