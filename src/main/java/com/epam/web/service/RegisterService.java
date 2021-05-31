package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.RegisterDao;
import com.epam.web.entities.Register;
import com.epam.web.enums.DaoType;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class RegisterService {

    private final DaoHelperFactory daoHelperFactory;
    private final DaoType daoType;

    public RegisterService(DaoHelperFactory daoHelperFactory, DaoType daoType) {
        this.daoHelperFactory = daoHelperFactory;
        this.daoType = daoType;
    }

    public Optional<Register> findById(long id) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            RegisterDao registerDao = (RegisterDao) helper.createDao(daoType);

            return registerDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Register> findByApplicationId(long id) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            RegisterDao registerDao = (RegisterDao) helper.createDao(daoType);

            return registerDao.findByApplicationId(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Register> findLimitedNumberOfRegisters(int offset, int numberOfRecords) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            RegisterDao registerDao = (RegisterDao) helper.createDao(daoType);

            return registerDao.findLimitedNumberOfEntities(offset, numberOfRecords);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void deleteById(long id) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            RegisterDao registerDao = (RegisterDao) helper.createDao(daoType);

            registerDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void deleteByApplicationId(long id) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            RegisterDao registerDao = (RegisterDao) helper.createDao(daoType);

            registerDao.deleteByApplicationId(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void save(Register register) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            RegisterDao registerDao = (RegisterDao) helper.createDao(daoType);

            registerDao.save(register);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
