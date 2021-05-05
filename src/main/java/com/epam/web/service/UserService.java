package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.UserDao;
import com.epam.web.entities.User;
import com.epam.web.enums.DaoType;
import com.epam.web.exception.ServiceException;

import java.util.Optional;

public class UserService {

    private final DaoHelperFactory daoHelperFactory;

    public UserService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<User> login(String login, String password) throws ServiceException {

        try(DaoHelper helper = daoHelperFactory.create()) {
            UserDao userDao = (UserDao) helper.createDao(DaoType.USER);

            return userDao.findByLoginAndPassword(login, password);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}