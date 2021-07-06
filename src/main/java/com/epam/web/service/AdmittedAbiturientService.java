package com.epam.web.service;

import com.epam.web.dao.AdmittedAbiturientDao;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.entities.AdmittedAbiturient;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import java.util.List;

public class AdmittedAbiturientService {

    private final DaoHelperFactory daoHelperFactory;

    private static final String ADMITTED_ABITURIENT_DAO = "ADMITTED_ABITURIENT_DAO";

    public AdmittedAbiturientService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<AdmittedAbiturient> findLimitedNumberOfAdmittedAbiturients(int offset, int numberOfRecords) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            AdmittedAbiturientDao admittedAbiturientDao = (AdmittedAbiturientDao) helper.createDao(ADMITTED_ABITURIENT_DAO);

            return admittedAbiturientDao.findLimitedNumberOfEntities(offset, numberOfRecords);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public int countAdmittedAbiturients() throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            AdmittedAbiturientDao admittedAbiturientDao = (AdmittedAbiturientDao) helper.createDao(ADMITTED_ABITURIENT_DAO);

            return admittedAbiturientDao.findAll().size();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void deleteByFacultyId(long facultyId) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            AdmittedAbiturientDao admittedAbiturientDao = (AdmittedAbiturientDao) helper.createDao(ADMITTED_ABITURIENT_DAO);

            admittedAbiturientDao.deleteByFacultyId(facultyId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void deleteByUserId(long userId) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            AdmittedAbiturientDao admittedAbiturientDao = (AdmittedAbiturientDao) helper.createDao(ADMITTED_ABITURIENT_DAO);

            admittedAbiturientDao.deleteByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void save(AdmittedAbiturient admittedAbiturient) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            AdmittedAbiturientDao admittedAbiturientDao = (AdmittedAbiturientDao) helper.createDao(ADMITTED_ABITURIENT_DAO);

            admittedAbiturientDao.save(admittedAbiturient);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
