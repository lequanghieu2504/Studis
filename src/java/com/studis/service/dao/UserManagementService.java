package com.studis.service.dao;

import com.studis.dao.UserManagementDAO;
import com.studis.exception.daoException.EntityNotFoundException;
import com.studis.exception.serviceException.BusinessRuleViolationException;
import com.studis.exception.serviceException.ServiceException;
import com.studis.model.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for managing user operations such as creation and deletion.
 * This class handles the business logic for user management and interacts with DAO to perform database operations.
 */
@Service
public class UserManagementService extends BaseDAOService {

    private final UserManagementDAO udao;  // DAO for user management operations
    private final UserRetrievalService urs;  // Service for retrieving user-related data

    /**
     * Constructs the UserManagementService with dependencies injected via Spring.
     * @param udao The DAO responsible for user-related database operations
     * @param urs The service used for checking user existence
     */
    @Autowired
    public UserManagementService(UserManagementDAO udao, UserRetrievalService urs) {
        this.udao = udao;
        this.urs = urs;
    }

    /**
     * Handles the user registration process by checking if the username and email are available,
     * and if so, creating a new user in the database.
     * @param user The user entity to be created
     * @throws ServiceException if an error occurs during user creation
     * @throws BusinessRuleViolationException if the username or email is already taken
     */
    public void handleCreate(User user) {
        try {
            beginTransaction();  // Start the transaction

            // Check if the username is already taken
            if (urs.handleExist("user_name", user.getName())) {
                throw new BusinessRuleViolationException(
                        "Username '" + user.getName() + "' is already taken. Please choose a different username.");
            }

            // Check if the email is already registered
            if (urs.handleExist("user_email", user.getEmail())) {
                throw new BusinessRuleViolationException(
                        "Email '" + user.getEmail() + "' is already registered. Please use a different email address.");
            }

            // Proceed with creating the user if both username and email are available
            udao.create(user);
            commitTransaction();  // Commit the transaction if creation is successful
        } catch (Exception e) {
            rollbackTransaction();  // Rollback the transaction in case of error
            throw new ServiceException(
                    "Unexpected error occurred while creating user. User details: " + user.toString(), e);
        } finally {
            closeConnection();  // Close the connection regardless of success or failure
        }
    }

    /**
     * Handles the deletion of a user by their ID. Checks if the user exists before proceeding.
     * @param id The ID of the user to be deleted
     * @throws ServiceException if an error occurs during user deletion
     * @throws EntityNotFoundException if no user exists with the provided ID
     */
    public void handleDeleteUser(int id) {
        try {
            beginTransaction();  // Start the transaction

            // Check if the user exists before attempting to delete
            if (!urs.handleExist("id", id)) {
                throw new EntityNotFoundException(
                        "User with ID " + id + " does not exist and cannot be deleted.");
            }

            // Proceed with deleting the user if they exist
            udao.delete(id);
            commitTransaction();  // Commit the transaction if deletion is successful
        } catch (Exception e) {
            rollbackTransaction();  // Rollback the transaction in case of error
            throw new ServiceException(
                    "Unexpected error occurred while deleting user with ID " + id + ".", e);
        } finally {
            closeConnection();  // Close the connection regardless of success or failure
        }
    }
}
