package com.epam.web.mapper;

import com.epam.web.entities.Register;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterRowMapper implements Mapper<Register> {

    private static final String ID = "ID";
    private static final String USER_ID = "USER_ID";
    private static final String FACULTY_ID = "FACULTY_ID";
    private static final String SCORE_SUM = "SCORE_SUM";
    private static final String IS_APPROVED = "IS_APPROVED";

    @Override
    public Register map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        Long userId = resultSet.getLong(USER_ID);
        Long facultyId = resultSet.getLong(FACULTY_ID);
        Integer scoreSum = resultSet.getInt(SCORE_SUM);
        Boolean isApproved = resultSet.getBoolean(IS_APPROVED);

        return new Register(id, userId, facultyId, scoreSum, isApproved);
    }
}
