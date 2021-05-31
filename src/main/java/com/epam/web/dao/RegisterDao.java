package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entities.Register;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.RegisterRowMapper;

import java.util.Optional;

public class RegisterDao extends AbstractDao<Register> implements Dao<Register> {

    private static final String FIND_BY_APPLICATION_ID = "SELECT * FROM REGISTER WHERE APPLICATION_ID = ?";
    private static final String CREATE_REGISTER = "INSERT INTO REGISTER (USER_ID, APPLICATION_ID, IS_APPROVED) " +
            "VALUES (?, ?, ?)";
    private static final String UPDATE_REGISTER = "UPDATE REGISTER SET USER_ID = ?, APPLICATION_ID = ?, " +
            "IS_APPROVED = ? WHERE ID = ?";
    private static final String DELETE_BY_APPLICATION_ID = "DELETE FROM REGISTER WHERE APPLICATION_ID = ?";
    private static final String TABLE = "REGISTER";

    public RegisterDao(ProxyConnection connection) {
        super(connection, new RegisterRowMapper(), TABLE);
    }

    public Optional<Register> findByApplicationId(long id) throws DaoException {
        return executeSingleResultQuery(FIND_BY_APPLICATION_ID, id);
    }

    public void deleteByApplicationId(long id) throws DaoException {
        executeUpdate(DELETE_BY_APPLICATION_ID, id);
    }

    @Override
    protected void create(Register register) throws DaoException {
        executeUpdate(CREATE_REGISTER, register.getUserId(), register.getApplicationId(), register.isApproved());
    }

    @Override
    protected void update(Register register) throws DaoException {
        executeUpdate(UPDATE_REGISTER, register.getApplicationId(), register.isApproved(), register.getId());
    }
}
