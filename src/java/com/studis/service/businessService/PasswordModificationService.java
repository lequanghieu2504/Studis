/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studis.service.businessService;

import com.studis.exception.daoException.DataAccessException;
import com.studis.exception.daoException.DatabaseConnectionException;
import com.studis.exception.daoException.EntityNotFoundException;
import com.studis.exception.serviceException.BusinessRuleViolationException;
import com.studis.exception.serviceException.ServiceException;
import com.studis.service.daoService.UserRetrievalService;
import com.studis.service.daoService.UserUpdateService;
import com.studis.utils.hashingUtils.HashingUtils;

/**
 *
 * @author ho huy
 */
public class PasswordModificationService {

    private final UserUpdateService uus = new UserUpdateService();
    private final UserRetrievalService urs = new UserRetrievalService();

    public void updatePassword(String email, String password, String confirmPassword) {
        try {
            int id = urs.handleRetrieveId("user_email", email);

            if (!password.equals(confirmPassword)) {
                throw new BusinessRuleViolationException("Passwords do not match.");
            }

            String salt = HashingUtils.generateSalt();
            String hashedPassword = HashingUtils.hashPassword(password, salt);

            uus.handleUpdatePassword(id, hashedPassword, salt);
        } catch (BusinessRuleViolationException e) {
            throw e;
        } catch (EntityNotFoundException e) {
            throw new ServiceException("User with the provided email not found.", e);
        } catch (DataAccessException | DatabaseConnectionException e) {
            throw new ServiceException("Database error occurred while updating password.", e);
        } catch (Exception e) {
            throw new ServiceException("An unexpected error occurred while updating the password.", e);
        }
    }
}

