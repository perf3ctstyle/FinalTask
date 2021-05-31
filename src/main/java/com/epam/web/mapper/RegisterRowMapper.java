package com.epam.web.mapper;

import com.epam.web.entities.Register;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterRowMapper implements Mapper<Register> {

    private static final String ID = "ID";
    private static final String USER_ID = "USER_ID";
    private static final String APPLICATION_ID = "APPLICATION_ID";
    private static final String IS_APPROVED = "IS_APPROVED";

    @Override
    public Register map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        Long userId = resultSet.getLong(USER_ID);
        Long applicationId = resultSet.getLong(APPLICATION_ID);
        Boolean isApproved = resultSet.getBoolean(IS_APPROVED);

        return new Register(id, userId, applicationId, isApproved);
    }
}
