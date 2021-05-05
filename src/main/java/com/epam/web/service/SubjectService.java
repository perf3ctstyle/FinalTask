package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.SubjectDAO;
import com.epam.web.entities.Subject;
import com.epam.web.enums.DaoType;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SubjectService {

    private final DaoHelperFactory daoHelperFactory;

    public SubjectService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<Subject> findAll() throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {

            SubjectDAO subjectDao = (SubjectDAO) helper.createDao(DaoType.SUBJECT);
            return subjectDao.findAll();
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }
}
