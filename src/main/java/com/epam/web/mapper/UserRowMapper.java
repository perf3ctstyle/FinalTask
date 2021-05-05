package com.epam.web.mapper;

import com.epam.web.entities.User;
import com.epam.web.enums.UserFields;
import com.epam.web.enums.UserRoles;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements Mapper<User> {
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(UserFields.ID.toString());

        String login = resultSet.getString(UserFields.LOGIN.toString());
        String password = resultSet.getString(UserFields.PASSWORD.toString());

        String stringRole = resultSet.getString(UserFields.ROLE.toString());
        UserRoles userRole = UserRoles.valueOf(stringRole);

        boolean isBlocked = resultSet.getBoolean(UserFields.IS_BLOCKED.toString());

        return new User(id, login, password, userRole, isBlocked);
    }
}
