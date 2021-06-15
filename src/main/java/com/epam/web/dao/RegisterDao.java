package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entities.Register;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.RegisterRowMapper;

import java.util.List;
import java.util.Optional;

public class RegisterDao extends AbstractDao<Register> implements Dao<Register> {

    private static final String FIND_BY_USER_ID = "SELECT * FROM REGISTER WHERE USER_ID = ?";
    private static final String FIND_ADMITTED_ABITURIENTS = "SELECT * FROM REGISTER WHERE FACULTY_ID = ? AND IS_APPROVED = TRUE " +
            "ORDER BY SCORE_SUM DESC LIMIT ?";
    private static final String CREATE_REGISTER = "INSERT INTO REGISTER (USER_ID, FACULTY_ID, SCORE_SUM, IS_APPROVED) " +
            "VALUES (?, ?, ?, ?)";
    private static final String UPDATE_REGISTER = "UPDATE REGISTER SET USER_ID = ?, FACULTY_ID = ?, " +
            "SCORE_SUM = ?, IS_APPROVED = ? WHERE ID = ?";
    private static final String DELETE_BY_USER_ID = "DELETE FROM REGISTER WHERE USER_ID = ?";
    private static final String TABLE = "REGISTER";

    public RegisterDao(ProxyConnection connection) {
        super(connection, new RegisterRowMapper(), TABLE);
    }

    public Optional<Register> findByUserId(long id) throws DaoException {
        return executeSingleResultQuery(FIND_BY_USER_ID, id);
    }

    public void deleteByUserId(long id) throws DaoException {
        executeUpdate(DELETE_BY_USER_ID, id);
    }

    public List<Register> findAdmittedAbiturientsRegistersForFaculty(long facultyId, int admissionPlan) throws DaoException {
        return executeQuery(FIND_ADMITTED_ABITURIENTS, facultyId, admissionPlan);
    }

    @Override
    protected void create(Register register) throws DaoException {
        executeUpdate(CREATE_REGISTER,
                register.getUserId(),
                register.getFacultyId(),
                register.getScoreSum(),
                register.isApproved());
    }

    @Override
    protected void update(Register register) throws DaoException {
        executeUpdate(UPDATE_REGISTER,
                register.getUserId(),
                register.getFacultyId(),
                register.getScoreSum(),
                register.isApproved(),
                register.getId());
    }
}
