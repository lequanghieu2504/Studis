package com.studis.service.business;

import com.studis.exception.serviceException.BusinessRuleViolationException;
import com.studis.model.entity.User;
import com.studis.service.dao.UserRetrievalService;
import com.studis.service.dao.UserUpdateService;
import com.studis.utils.hashingUtils.HashingUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for handling user login logic.
 * This service is responsible for verifying user credentials, including username/email and password,
 * and retrieving the corresponding user entity.
 */
@Service
public class LoginService {

    private final UserRetrievalService urs; // Service to retrieve user data from the database
    private final UserUpdateService uus;   // Service to update user data (e.g., last login time)

    /**
     * Constructor to inject dependencies.
     * @param urs UserRetrievalService for retrieving user details
     * @param uus UserUpdateService for updating user details after login
     */
    @Autowired
    public LoginService(UserRetrievalService urs, UserUpdateService uus) {
        this.urs = urs;
        this.uus = uus;
    }
    
    /**
     * Handles the login process by verifying the user's credentials (username/email and password),
     * retrieving the corresponding user, and ensuring the password is correct.
     * 
     * @param nameOrEmail The username or email provided by the user for login
     * @param password The password provided by the user for authentication
     * @return The user object if login is successful
     * @throws BusinessRuleViolationException if the login credentials are invalid
     */
    public User login(String nameOrEmail, String password) {
        
        // Retrieve the user by their username/email
        User user = urs.handleRetrieve(nameOrEmail);
        
        // Update any relevant user data (e.g., last login time)
        uus.handleUpdate(user);

        // Verify the password matches the stored hash and salt
        if (!HashingUtils.verifyPassword(password, user.getSalt(), user.getHashedPassword())) {
            throw new BusinessRuleViolationException("Invalid password.");
        }

        // Return the user object if credentials are valid
        return user;
    }
}
