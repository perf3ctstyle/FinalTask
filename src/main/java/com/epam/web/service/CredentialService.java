package com.epam.web.service;

import com.epam.web.dao.CredentialDao;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.entities.Credential;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class CredentialService {

    private final DaoHelperFactory daoHelperFactory;

    private static final String CREDENTIAL_DAO = "CREDENTIAL_DAO";

    public CredentialService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<Credential> findById(long id) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            CredentialDao credentialDao = (CredentialDao) helper.createDao(CREDENTIAL_DAO);

            return credentialDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Credential> findLimitedNumberOfCredentials(int offset, int numberOfRecords) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            CredentialDao credentialDao = (CredentialDao) helper.createDao(CREDENTIAL_DAO);

            return credentialDao.findLimitedNumberOfEntities(offset, numberOfRecords);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Credential> findByUserId(long id) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            CredentialDao credentialDao = (CredentialDao) helper.createDao(CREDENTIAL_DAO);

            return credentialDao.findByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void deleteByUserId(long userId) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            CredentialDao credentialDao = (CredentialDao) helper.createDao(CREDENTIAL_DAO);

            credentialDao.deleteByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public int countCredentials() throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            CredentialDao credentialDao = (CredentialDao) helper.createDao(CREDENTIAL_DAO);

            return credentialDao.findAll().size();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void save(Credential credential) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            CredentialDao credentialDao = (CredentialDao) helper.createDao(CREDENTIAL_DAO);

            credentialDao.save(credential);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
