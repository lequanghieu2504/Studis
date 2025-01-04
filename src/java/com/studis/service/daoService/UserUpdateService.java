package com.studis.service.daoService;

import com.studis.dao.UserUpdateDAO;
import com.studis.exception.daoException.DataAccessException;
import com.studis.exception.daoException.DatabaseConnectionException;
import com.studis.exception.daoException.EntityNotFoundException;
import com.studis.exception.serviceException.BusinessRuleViolationException;
import com.studis.exception.serviceException.ServiceException;
import com.studis.exception.serviceException.TransactionException;
import com.studis.model.entity.User;

public class UserUpdateService extends BaseDAOService{

    private final UserUpdateDAO udao = new UserUpdateDAO();
    private final UserRetrievalService urs = new UserRetrievalService();

    public void handleUpdateNameOrEmail(int id, String field, String value) {
        try {
            beginTransaction();
            User user = urs.handleRetrieve(id);

            if (urs.handleExist(field, value)) {
                throw new BusinessRuleViolationException("The value for field '" + field + "' is already taken.");
            }

            switch (field) {
                case "user_name":
                    user.setName(value);
                    break;
                case "user_email":
                    user.setEmail(value);
                    break;
                default:
                    throw new BusinessRuleViolationException("Invalid field '" + field + "'. Allowed fields: 'user_name', 'user_email'.");
            }

            udao.update(user);
            commitTransaction();
        } catch (DataAccessException | DatabaseConnectionException | EntityNotFoundException e) {
            rollbackTransaction();
            throw new ServiceException("Error occurred during data access while updating user.", e);
        } catch (TransactionException e) {
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            rollbackTransaction();
            throw new ServiceException("Unexpected error occurred while updating user.", e);
        } finally {
            closeConnection();
        }
    }

    public void handleUpdatePassword(int id, String hashedPassword, String salt) {
        try {
            beginTransaction();
            User user = urs.handleRetrieve(id);

            user.setHashedPassword(hashedPassword);
            user.setSalt(salt);

            udao.update(user);
            commitTransaction();
        } catch (DataAccessException | DatabaseConnectionException | EntityNotFoundException e) {
            rollbackTransaction();
            throw new ServiceException("Error occurred during data access while updating password.", e);
        } catch (TransactionException e) {
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            rollbackTransaction();
            throw new ServiceException("Unexpected error occurred while updating password.", e);
        } finally {
            closeConnection();
        }
    }

    public void handleUpdate(User user) {
        try {
            beginTransaction();
            urs.handleRetrieve(user.getId());

            udao.update(user);
            commitTransaction();
        } catch (DataAccessException | DatabaseConnectionException | EntityNotFoundException e) {
            rollbackTransaction();
            throw new ServiceException("Error occurred during data access while updating password.", e);
        } catch (TransactionException e) {
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            rollbackTransaction();
            throw new ServiceException("Unexpected error occurred while updating password.", e);
        } finally {
            closeConnection();
        }
    }
}
