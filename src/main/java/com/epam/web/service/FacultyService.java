package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.FacultyDAO;
import com.epam.web.entities.Faculty;
import com.epam.web.enums.DaoType;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FacultyService {

    private final DaoHelperFactory daoHelperFactory;

    public FacultyService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<Faculty> findById(long id) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            FacultyDAO facultyDao = (FacultyDAO) helper.createDao(DaoType.FACULTY);

            return facultyDao.findById(id);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public List<Faculty> findNumberOfFaculties(int offset, int numberOfRecords) throws ServiceException {

        try(DaoHelper helper = daoHelperFactory.create()) {
            FacultyDAO facultyDao = (FacultyDAO) helper.createDao(DaoType.FACULTY);

            return facultyDao.findLimitedNumberOfFaculties(offset, numberOfRecords);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public int countFaculties() throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            FacultyDAO facultyDao = (FacultyDAO) helper.createDao(DaoType.FACULTY);

            return facultyDao.findAll().size();
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }
}
