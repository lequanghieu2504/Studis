package com.studis.service.dao;

import com.studis.dao.UserUpdateDAO;
import com.studis.exception.serviceException.ServiceException;
import com.studis.model.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling user updates in the database.
 * This class provides methods to update user attributes like name, email, password,
 * and any other user details.
 */
@Service
public class UserUpdateService extends BaseDAOService {

    private final UserUpdateDAO udao;  // DAO for updating user data
    private final UserRetrievalService urs;  // Service for retrieving user data

    /**
     * Constructs the UserUpdateService with the DAO dependencies injected via Spring.
     * @param udao The DAO responsible for updating user data
     * @param urs The service responsible for retrieving user data
     */
    @Autowired
    public UserUpdateService(UserUpdateDAO udao, UserRetrievalService urs) {
        this.udao = udao;
        this.urs = urs;
    }

    /**
     * Handles updating a user's name or email.
     * Ensures that the new name or email is unique before applying the update.
     * @param id The ID of the user to be updated
     * @param field The field to be updated (either 'user_name' or 'user_email')
     * @param value The new value for the field
     * @throws ServiceException if the value is already taken or an unexpected error occurs
     */
    public void handleUpdateNameOrEmail(int id, String field, String value) {
        try {
            beginTransaction();  // Start a new transaction
            User user = urs.handleRetrieve(id);  // Retrieve the user to be updated

            // Check if the new value for the field is already taken
            if (urs.handleExist(field, value)) {
                throw new ServiceException("The value for field '" + field + "' is already taken.");
            }

            // Update the appropriate field based on the 'field' parameter
            switch (field) {
                case "user_name":
                    user.setName(value);
                    break;
                case "user_email":
                    user.setEmail(value);
                    break;
                default:
                    throw new ServiceException("Invalid field '" + field + "'. Allowed fields: 'user_name', 'user_email'.");
            }

            // Save the updated user details
            udao.update(user);
            commitTransaction();  // Commit the transaction if the update is successful
        } catch (Exception e) {
            rollbackTransaction();  // Rollback transaction if an error occurs
            throw new ServiceException("Unexpected error occurred while updating user: " + field + " = " + value, e);
        } finally {
            closeConnection();  // Close the connection after the operation
        }
    }

    /**
     * Handles updating a user's password.
     * @param id The ID of the user whose password is being updated
     * @param hashedPassword The new hashed password
     * @param salt The salt used to hash the password
     * @throws ServiceException if an error occurs while updating the password
     */
    public void handleUpdatePassword(int id, String hashedPassword, String salt) {
        try {
            beginTransaction();  // Start a new transaction
            User user = urs.handleRetrieve(id);  // Retrieve the user to be updated

            // Update the user's password and salt
            user.setHashedPassword(hashedPassword);
            user.setSalt(salt);

            // Save the updated user details
            udao.update(user);
            commitTransaction();  // Commit the transaction if the update is successful
        } catch (Exception e) {
            rollbackTransaction();  // Rollback transaction if an error occurs
            throw new ServiceException("Unexpected error occurred while updating password for user ID: " + id, e);
        } finally {
            closeConnection();  // Close the connection after the operation
        }
    }

    /**
     * Handles updating the entire user object.
     * @param user The user object containing the updated details
     * @throws ServiceException if an error occurs while updating the user
     */
    public void handleUpdate(User user) {
        try {
            beginTransaction();  // Start a new transaction
            urs.handleRetrieve(user.getId());  // Retrieve the user to be updated

            // Save the updated user details
            udao.update(user);
            commitTransaction();  // Commit the transaction if the update is successful
        } catch (Exception e) {
            rollbackTransaction();  // Rollback transaction if an error occurs
            throw new ServiceException("Unexpected error occurred while updating user ID: " + user.getId(), e);
        } finally {
            closeConnection();  // Close the connection after the operation
        }
    }
}
