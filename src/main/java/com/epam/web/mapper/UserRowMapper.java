package com.epam.web.mapper;

import com.epam.web.entities.User;
import com.epam.web.enums.UserFields;
import com.epam.web.enums.UserRoles;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements Mapper<User> {
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(UserFields.ID.getName());

        String login = resultSet.getString(UserFields.LOGIN.getName());
        String password = resultSet.getString(UserFields.PASSWORD.getName());

        String stringRole = resultSet.getString(UserFields.USER_ROLE.getName());
        UserRoles userRole = UserRoles.valueOf(stringRole);

        return new User(id, login, password, userRole);
    }
}
