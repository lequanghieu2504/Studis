package com.studis.model.dto;

/**
 * Data Transfer Object (DTO) for user registration requests.
 * This class encapsulates the user input required for registration, 
 * including name, email, password, and confirmation of the password.
 * 
 * It is used to collect data from the client-side during the registration process
 * and send it to the server for validation and processing.
 */
public class RegisterDTO {
    
    // The name of the user registering
    private final String name;
    
    // The email address of the user registering
    private final String email;
    
    // The password chosen by the user for registration
    private final String password;
    
    // The confirmation of the chosen password to ensure correctness
    private final String confirmPassword;

    /**
     * Constructs a RegisterDTO object with the provided name, email, password,
     * and confirmation password.
     * 
     * @param name The name of the user
     * @param email The email of the user
     * @param password The password chosen by the user
     * @param confirmPassword The confirmation of the password
     */
    public RegisterDTO(String name, String email, String password, String confirmPassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    /**
     * Gets the name of the user.
     * 
     * @return The name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the email of the user.
     * 
     * @return The email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the password chosen by the user.
     * 
     * @return The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the confirmation of the password.
     * 
     * @return The confirmation password
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }
}
