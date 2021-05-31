package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.UserDao;
import com.epam.web.entities.User;
import com.epam.web.enums.DaoType;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import java.util.Optional;

public class UserService {

    private final DaoHelperFactory daoHelperFactory;
    private final DaoType daoType;

    public UserService(DaoHelperFactory daoHelperFactory, DaoType daoType) {
        this.daoHelperFactory = daoHelperFactory;
        this.daoType = daoType;
    }

    public Optional<User> login(String login, String password) throws ServiceException {

        try(DaoHelper helper = daoHelperFactory.create()) {
            UserDao userDao = (UserDao) helper.createDao(daoType);

            return userDao.findByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<User> findById(long id) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao userDao = (UserDao) helper.createDao(daoType);

            return userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void changeIsBlocked(long id, boolean isBlocked) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao userDao = (UserDao) helper.createDao(daoType);

            userDao.changeIsBlocked(id, isBlocked);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void deleteById(long id) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao userDao = (UserDao) helper.createDao(daoType);

            userDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void save(User user) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao userDao = (UserDao) helper.createDao(daoType);

            userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}