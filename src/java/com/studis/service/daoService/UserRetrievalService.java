/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studis.service.daoService;

import com.studis.dao.UserRetrievalDAO;
import com.studis.exception.daoException.DataAccessException;
import com.studis.exception.daoException.DatabaseConnectionException;
import com.studis.exception.daoException.EntityNotFoundException;
import com.studis.exception.serviceException.ServiceException;
import com.studis.model.entity.User;

/**
 *
 * @author ho huy
 */
public class UserRetrievalService extends BaseDAOService {

    private final UserRetrievalDAO udao = new UserRetrievalDAO();

    public User handleRetrieve(int id){
        try {
            return udao.retrieve(id);
        } catch (DataAccessException | DatabaseConnectionException e) {
            throw new ServiceException("Error occurred while retrieving user by ID.", e);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Unexpected error occurred while retrieving user by ID.", e);
        }
    }

    public User handleRetrieve(String nameOrEmail){
        try {
            return udao.retrieve(nameOrEmail);
        } catch (DataAccessException | DatabaseConnectionException e) {
            throw new ServiceException("Error occurred while retrieving user by name or email.", e);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Unexpected error occurred while retrieving user by name or email.", e);
        }
    }

    public User handleRetrieve(String field, String value){
        try {
            return udao.retrieve(field, value);
        } catch (DataAccessException | DatabaseConnectionException e) {
            throw new ServiceException("Error occurred while retrieving user by " + field, e);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Unexpected error occurred while retrieving user by " + field, e);
        }
    }

    public <T> int handleRetrieveId(String field, T value){
        try {
            User user = udao.retrieve(field, value);
            return user.getId();
        } catch (DataAccessException | DatabaseConnectionException e) {
            throw new ServiceException("Error occurred while retrieving user ID by " + field, e);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Unexpected error occurred while retrieving user ID by " + field, e);
        }
    }

    public <T> boolean handleExist(String field, T value){
        return udao.exist(field, value);
    }
}
