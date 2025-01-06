package com.studis.model.dto;

/**
 * Data Transfer Object (DTO) representing login credentials for user authentication.
 * This class holds the user's username or email and password that are required for login.
 * 
 * Typically used in authentication processes where the client provides the login data,
 * and it is passed to the server for validation.
 */
public class LoginDTO {

    // The username or email used by the user to log in
    private final String nameOrEmail;
    
    // The password entered by the user for authentication
    private final String password;

    /**
     * Constructs a LoginDTO object with the provided username/email and password.
     * 
     * @param nameOrEmail The username or email used for login
     * @param password The password for authentication
     */
    public LoginDTO(String nameOrEmail, String password) {
        this.nameOrEmail = nameOrEmail;
        this.password = password;
    }

    /**
     * Retrieves the username or email used for login.
     * 
     * @return The username or email
     */
    public String getNameOrEmail() {
        return nameOrEmail;
    }

    /**
     * Retrieves the password used for authentication.
     * 
     * @return The password
     */
    public String getPassword() {
        return password;
    }
}
