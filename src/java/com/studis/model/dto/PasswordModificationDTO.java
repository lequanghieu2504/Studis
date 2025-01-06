package com.studis.model.dto;

/**
 * Data Transfer Object (DTO) for handling password modification requests.
 * This class encapsulates the email, new password, and confirm password
 * provided by the user when modifying their password.
 * 
 * It can be used for both password modification processes, where both the 
 * new password and its confirmation are required, and for email-based actions
 * where only the email is involved.
 */
public class PasswordModificationDTO {
    
    // The email associated with the account
    private final String email;
    
    // The new password the user wishes to set
    private final String password;
    
    // The confirmation of the new password to ensure correctness
    private final String confirmPassword;

    /**
     * Constructs a PasswordModificationDTO object with the provided email,
     * new password, and confirmation password.
     * 
     * @param email The email of the user requesting the password modification
     * @param password The new password
     * @param confirmPassword The confirmation of the new password
     */
    public PasswordModificationDTO(String email, String password, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
    
    /**
     * Constructs a PasswordModificationDTO object with the provided email only.
     * This constructor is used for cases where only the email is involved (e.g., password reset requests).
     * 
     * @param email The email of the user
     */
    public PasswordModificationDTO(String email) {
        this.email = email;
        this.password = null;
        this.confirmPassword = null;
    }

    /**
     * Gets the email associated with the account.
     * 
     * @return The email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the new password that the user wants to set.
     * 
     * @return The new password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the confirmation of the new password.
     * 
     * @return The confirmation password
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }
}
