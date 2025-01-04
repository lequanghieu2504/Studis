/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studis.service.businessService;

import com.studis.exception.daoException.DataAccessException;
import com.studis.exception.daoException.DatabaseConnectionException;
import com.studis.exception.serviceException.BusinessRuleViolationException;
import com.studis.exception.serviceException.ServiceException;
import com.studis.model.entity.User;
import com.studis.service.daoService.UserManagementService;
import com.studis.utils.hashingUtils.HashingUtils;

/**
 *
 * @author ho huy
 */
public class RegisterService {

    private final UserManagementService ums = new UserManagementService();

    public void register(String name, String email, String password, String confirmPassword) {
        try {
            if (!password.equals(confirmPassword)) {
                throw new BusinessRuleViolationException("Passwords do not match.");
            }

            String salt = HashingUtils.generateSalt();
            String hashedPassword = HashingUtils.hashPassword(password, salt);

            ums.handleCreate(new User(name, email, hashedPassword, salt));
        } catch (BusinessRuleViolationException e) {
            throw e;
        } catch (DataAccessException | DatabaseConnectionException e) {
            throw new ServiceException("Database error occurred while registering user.", e);
        } catch (Exception e) {
            throw new ServiceException("An unexpected error occurred during registration.", e);
        }
    }
}