package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entities.Subject;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.SubjectRowMapper;

public class SubjectDAO extends AbstractDao<Subject> implements Dao<Subject> {

    private static final String TABLE = "SUBJECT";

    public SubjectDAO(ProxyConnection connection) {
        super(connection, new SubjectRowMapper(), TABLE);
    }

    @Override
    protected void create(Subject subject) throws DaoException {

    }

    @Override
    protected void update(Subject subject) throws DaoException {

    }
}
