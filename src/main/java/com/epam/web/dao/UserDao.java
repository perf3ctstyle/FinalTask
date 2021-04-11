package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entities.User;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.Mapper;
import com.epam.web.mapper.UserRowMapper;

import java.util.Optional;

public class UserDao extends AbstractDao<User> implements Dao<User> {

    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM USER WHERE LOGIN = ? AND PASSWORD = ?";
    private static final String FIND_BY_LOGIN = "SELECT * FROM USER WHERE LOGIN = ?";
    private static final String CREATE_USER = "INSERT INTO USER (LOGIN, PASSWORD, ROLE) values (?, md5(?), ?)";
    private static final String UPDATE_USER = "UPDATE USER SET USERNAME = ?, PASSWORD = MD5(?), ROLE = ? WHERE ID = ?";
    private static final String TABLE_NAME = "USER";

    public UserDao(ProxyConnection connection) {
        super(connection, new UserRowMapper(), TABLE_NAME);
    }

    public Optional<User> findByLoginAndPassword(final String login, final String password) throws DaoException {
        return executeSingleResultQuery(FIND_BY_LOGIN_AND_PASSWORD, login, password);
    }

    public Optional<User> findByLogin(final String login) throws DaoException {
        return executeSingleResultQuery(FIND_BY_LOGIN, login);
    }

    protected void create(User user) throws DaoException {
        executeUpdate(CREATE_USER, user.getLogin(), user.getPassword(), user.getUserRole().toString());
    }

    protected void update(User user) throws DaoException {
        Optional<User> optionalUser = findById(user.getId());
        executeUpdate(UPDATE_USER, user.getLogin(), user.getPassword(), user.getUserRole().toString(), user.getId());
    }
 }
