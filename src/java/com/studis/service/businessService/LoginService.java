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
import com.studis.model.entity.User;
import com.studis.service.daoService.UserRetrievalService;
import com.studis.service.daoService.UserUpdateService;
import com.studis.utils.hashingUtils.HashingUtils;

/**
 *
 * @author ho huy
 */
public class LoginService {

    private final UserRetrievalService urs = new UserRetrievalService();
    private final UserUpdateService uus = new UserUpdateService();

    public User login(String nameOrEmail, String password) {
        try {
            User user = urs.handleRetrieve(nameOrEmail);
            uus.handleUpdate(user);

            if (!HashingUtils.verifyPassword(password, user.getSalt(), user.getHashedPassword())) {
                throw new BusinessRuleViolationException("Invalid password.");
            }

            return user;
        } catch (EntityNotFoundException e) {
            throw new ServiceException("User not found with the given username or email.", e);
        } catch (BusinessRuleViolationException e) {
            throw e;
        } catch (DataAccessException | DatabaseConnectionException e) {
            throw new ServiceException("Database error occurred while retrieving user.", e);
        } catch (Exception e) {
            throw new ServiceException("An unexpected error occurred.", e);
        }
    }
}
