package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.FacultyDao;
import com.epam.web.entities.Faculty;
import com.epam.web.enums.DaoType;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class FacultyService {

    private final DaoHelperFactory daoHelperFactory;
    private final DaoType daoType;

    public FacultyService(DaoHelperFactory daoHelperFactory, DaoType daoType) {
        this.daoHelperFactory = daoHelperFactory;
        this.daoType = daoType;
    }

    public Optional<Faculty> findById(long id) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            FacultyDao facultyDao = (FacultyDao) helper.createDao(daoType);

            return facultyDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Faculty> findByName(String name) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            FacultyDao facultyDao = (FacultyDao) helper.createDao(daoType);

            return facultyDao.findByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Faculty> findLimitedNumberOfFaculties(int offset, int numberOfRecords) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            FacultyDao facultyDao = (FacultyDao) helper.createDao(daoType);

            return facultyDao.findLimitedNumberOfEntities(offset, numberOfRecords);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public int countFaculties() throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            FacultyDao facultyDao = (FacultyDao) helper.createDao(daoType);

            return facultyDao.findAll().size();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
