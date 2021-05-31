package com.epam.web.mapper;

import com.epam.web.entities.User;
import com.epam.web.enums.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements Mapper<User> {

    private static final String ID = "ID";
    private static final String LOGIN = "LOGIN";
    private static final String PASSWORD = "PASSWORD";
    private static final String ROLE = "ROLE";
    private static final String IS_BLOCKED = "IS_BLOCKED";

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);

        String login = resultSet.getString(LOGIN);
        String password = resultSet.getString(PASSWORD);

        String stringRole = resultSet.getString(ROLE);
        UserRole userRole = UserRole.valueOf(stringRole);

        boolean isBlocked = resultSet.getBoolean(IS_BLOCKED);

        return new User(id, login, password, userRole, isBlocked);
    }
}
