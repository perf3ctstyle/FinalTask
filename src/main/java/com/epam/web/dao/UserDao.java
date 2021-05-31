package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entities.Identifiable;
import com.epam.web.entities.User;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.Mapper;
import com.epam.web.mapper.UserRowMapper;

import java.util.List;
import java.util.Optional;

public class UserDao extends AbstractDao<User> {

    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM USER WHERE LOGIN = ? AND PASSWORD = md5(?)";
    private static final String FIND_BY_LOGIN = "SELECT * FROM USER WHERE LOGIN = ?";
    private static final String CREATE_USER = "INSERT INTO USER (LOGIN, PASSWORD, ROLE, IS_BLOCKED) values (?, md5(?), ?, ?)";
    private static final String UPDATE_USER = "UPDATE USER SET LOGIN = ?, PASSWORD = md5(?), ROLE = ?, IS_BLOCKED = ? WHERE ID = ?";
    private static final String UPDATE_IS_BLOCKED = "UPDATE USER SET IS_BLOCKED = ? WHERE ID = ?";
    private static final String TABLE = "USER";

    public UserDao(ProxyConnection connection) {
        super(connection, new UserRowMapper(), TABLE);
    }

    public Optional<User> findByLoginAndPassword(final String login, final String password) throws DaoException {
        return executeSingleResultQuery(FIND_BY_LOGIN_AND_PASSWORD, login, password);
    }

    public Optional<User> findByLogin(final String login) throws DaoException {
        return executeSingleResultQuery(FIND_BY_LOGIN, login);
    }

    public void changeIsBlocked(final long id, final boolean isBlocked) throws DaoException {
        executeUpdate(UPDATE_IS_BLOCKED, isBlocked, id);
    }

    protected void create(User user) throws DaoException {
        executeUpdate(CREATE_USER, user.getLogin(), user.getPassword(), user.getRole().toString());
    }

    protected void update(User user) throws DaoException {
        Optional<User> optionalUser = findById(user.getId());
        executeUpdate(UPDATE_USER,
                user.getLogin(),
                user.getPassword(),
                user.getRole().toString(),
                user.isBlocked(),
                user.getId());
    }
 }
