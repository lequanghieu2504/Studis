package com.studis.service.business;

import com.studis.exception.serviceException.BusinessRuleViolationException;
import com.studis.service.dao.UserRetrievalService;
import com.studis.service.dao.UserUpdateService;
import com.studis.utils.hashingUtils.HashingUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service responsible for handling password modification requests.
 * This service ensures that the new password is valid and updates the user's password in the system.
 */
@Service
public class PasswordModificationService {

    private final UserUpdateService uus; // Service to handle user data updates (e.g., updating password)
    private final UserRetrievalService urs; // Service to retrieve user data (e.g., checking if email exists)

    /**
     * Constructor to inject dependencies.
     * @param uus UserUpdateService for updating user data, including password
     * @param urs UserRetrievalService for retrieving user data
     */
    @Autowired
    public PasswordModificationService(UserUpdateService uus, UserRetrievalService urs) {
        this.uus = uus;
        this.urs = urs;
    }

    /**
     * Updates the user's password after verifying the new password and the confirmation password.
     * 
     * @param email The user's email address for identification
     * @param password The new password to be set
     * @param confirmPassword The confirmation of the new password to ensure they match
     * @throws BusinessRuleViolationException if the passwords do not match or any other rule is violated
     */
    public void updatePassword(String email, String password, String confirmPassword) {
        
        // Retrieve the user's ID based on their email address
        int id = urs.handleRetrieveId("user_email", email);

        // Check if the password and confirm password match
        if (!password.equals(confirmPassword)) {
            throw new BusinessRuleViolationException("Passwords do not match.");
        }

        // Generate a salt for password hashing
        String salt = HashingUtils.generateSalt();

        // Hash the new password using the generated salt
        String hashedPassword = HashingUtils.hashPassword(password, salt);

        // Update the user's password and salt in the database
        uus.handleUpdatePassword(id, hashedPassword, salt);
    }
}
