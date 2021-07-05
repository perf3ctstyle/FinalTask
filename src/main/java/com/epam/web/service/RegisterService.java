package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.RegisterDao;
import com.epam.web.entities.Register;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class RegisterService {

    private final DaoHelperFactory daoHelperFactory;

    private static final String REGISTER_DAO = "REGISTER_DAO";

    public RegisterService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<Register> findById(long id) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            RegisterDao registerDao = (RegisterDao) helper.createDao(REGISTER_DAO);

            return registerDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Register> findByUserId(long id) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            RegisterDao registerDao = (RegisterDao) helper.createDao(REGISTER_DAO);

            return registerDao.findByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Register> findLimitedNumberOfRegisters(int offset, int numberOfRecords) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            RegisterDao registerDao = (RegisterDao) helper.createDao(REGISTER_DAO);

            return registerDao.findLimitedNumberOfEntities(offset, numberOfRecords);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void deleteById(long id) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            RegisterDao registerDao = (RegisterDao) helper.createDao(REGISTER_DAO);

            registerDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void deleteByUserId(long userId) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            RegisterDao registerDao = (RegisterDao) helper.createDao(REGISTER_DAO);

            registerDao.deleteByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public int countRegisters() throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            RegisterDao registerDao = (RegisterDao) helper.createDao(REGISTER_DAO);

            return registerDao.findAll().size();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Register> findAdmittedAbiturientsRegistersForFaculty(long facultyId, int admissionPlan) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            RegisterDao registerDao = (RegisterDao) helper.createDao(REGISTER_DAO);

            return registerDao.findAdmittedAbiturientsRegistersForFaculty(facultyId, admissionPlan);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void save(Register register) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            RegisterDao registerDao = (RegisterDao) helper.createDao(REGISTER_DAO);

            registerDao.save(register);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
