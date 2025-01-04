/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studis.service.daoService;

import com.studis.dao.UserManagementDAO;
import com.studis.exception.daoException.DataAccessException;
import com.studis.exception.daoException.DatabaseConnectionException;
import com.studis.exception.daoException.EntityNotFoundException;
import com.studis.exception.serviceException.BusinessRuleViolationException;
import com.studis.exception.serviceException.ServiceException;
import com.studis.exception.serviceException.TransactionException;
import com.studis.model.entity.User;

/**
 *
 * @author ho huy
 */
public class UserManagementService extends BaseDAOService {

    private final UserManagementDAO udao = new UserManagementDAO();
    private final UserRetrievalService urs = new UserRetrievalService();

    public void handleCreate(User user) {
        try {
            beginTransaction();

            if (urs.handleExist("user_name", user.getName())) {
                throw new BusinessRuleViolationException("Username is already taken.");
            }

            if (urs.handleExist("user_email", user.getEmail())) {
                throw new BusinessRuleViolationException("Email is already registered.");
            }

            udao.create(user);
            commitTransaction();
        } catch (DataAccessException | DatabaseConnectionException | EntityNotFoundException e) {
            rollbackTransaction();
            throw new ServiceException("Error occurred during data access while creating user.", e);
        } catch (TransactionException e) {
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            rollbackTransaction();
            throw new ServiceException("Unexpected error occurred while creating user.", e);
        } finally {
            closeConnection();
        }
    }

    public void handleDeleteUser(int id) {
        try {
            beginTransaction();
            if(!urs.handleExist("user_id", id)){
                throw new EntityNotFoundException("");
            }
            udao.delete(id);
            commitTransaction();
        } catch (DataAccessException | DatabaseConnectionException | EntityNotFoundException e) {
            rollbackTransaction();
            throw new ServiceException("Error occurred during data access while deleting user.", e);
        } catch (TransactionException e) {
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            rollbackTransaction();
            throw new ServiceException("Unexpected error occurred while deleting user.", e);
        } finally {
            closeConnection();
        }
    }

}
