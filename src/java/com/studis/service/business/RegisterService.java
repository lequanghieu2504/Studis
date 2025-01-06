package com.studis.service.business;

import com.studis.exception.serviceException.BusinessRuleViolationException;
import com.studis.model.entity.User;
import com.studis.service.dao.UserManagementService;
import com.studis.utils.hashingUtils.HashingUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service responsible for handling user registration.
 * This service ensures that the user's data is valid before creating a new user in the system.
 */
@Service
public class RegisterService {

    private final UserManagementService ums; // Service to manage user creation and updates

    /**
     * Constructor to inject dependencies.
     * @param ums UserManagementService for handling user creation and related actions
     */
    @Autowired
    public RegisterService(UserManagementService ums) {
        this.ums = ums;
    }

    /**
     * Registers a new user after validating the provided data.
     * 
     * @param name The user's name
     * @param email The user's email address
     * @param password The password chosen by the user
     * @param confirmPassword The confirmation of the password to ensure they match
     * @throws BusinessRuleViolationException if the passwords do not match or any other rule is violated
     */
    public void register(String name, String email, String password, String confirmPassword) {
        
        // Check if the password and confirm password match
        if (!password.equals(confirmPassword)) {
            throw new BusinessRuleViolationException("Passwords do not match.");
        }

        // Generate a salt for password hashing
        String salt = HashingUtils.generateSalt();

        // Hash the password using the generated salt
        String hashedPassword = HashingUtils.hashPassword(password, salt);

        // Create a new user object and pass it to the UserManagementService for creation
        ums.handleCreate(new User(name, email, hashedPassword, salt));
    }
}
