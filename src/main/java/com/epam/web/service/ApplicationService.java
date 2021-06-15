package com.epam.web.service;

import com.epam.web.dao.ApplicationDao;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.entities.Application;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class ApplicationService {

    private final DaoHelperFactory daoHelperFactory;
    
    private static final String APPLICATION_DAO = "APPLICATION_DAO";

    public ApplicationService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<Application> findById(long id) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            ApplicationDao applicationDAO = (ApplicationDao) helper.createDao(APPLICATION_DAO);

            return applicationDAO.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Application> findByUserId(long userId) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            ApplicationDao applicationDao = (ApplicationDao) helper.createDao(APPLICATION_DAO);

            return applicationDao.findByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void deleteByUserId(long userId) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            ApplicationDao applicationDao = (ApplicationDao) helper.createDao(APPLICATION_DAO);

            applicationDao.deleteByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Application> findLimitedNumberOfApplications(int offset, int numberOfRecords) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            ApplicationDao applicationDao = (ApplicationDao) helper.createDao(APPLICATION_DAO);

            return applicationDao.findLimitedNumberOfEntities(offset, numberOfRecords);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public int countApplications() throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            ApplicationDao applicationDao = (ApplicationDao) helper.createDao(APPLICATION_DAO);

            return applicationDao.findAll().size();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void save(Application application) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            ApplicationDao applicationDao = (ApplicationDao) helper.createDao(APPLICATION_DAO);

            applicationDao.save(application);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
