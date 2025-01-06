package com.studis.utils.validatorUtils;

import com.studis.exception.commonException.ValidationException;

/**
 * This class validates a password based on several criteria:
 * - The password must be at least 8 characters long.
 * - The password must contain at least one uppercase letter.
 * - The password must contain at least one lowercase letter.
 * - The password must contain at least one digit.
 * - The password must match a defined regular expression for further security.
 */
public class PasswordValidator implements Validator<String> {

    // Regular expression for additional password validation (ensures mixed case and digit inclusion)
    private String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";

    /**
     * Validates the given password against predefined password strength rules.
     * 
     * @param value The password string to validate.
     * @throws ValidationException If the password doesn't meet the required criteria.
     */
    @Override
    public void validate(String value) {
        // Check if the password is null or empty
        if (value == null || value.isEmpty()) {
            throw new ValidationException("Value cannot be null or empty");
        }

        // Check if the password is at least 8 characters long
        if (value.length() < 8) {
            throw new ValidationException("Password must be at least 8 characters long");
        }

        // Ensure the password contains at least one uppercase letter
        if (!value.matches(".*[A-Z].*")) {
            throw new ValidationException("Password must contain at least one uppercase letter");
        }

        // Ensure the password contains at least one lowercase letter
        if (!value.matches(".*[a-z].*")) {
            throw new ValidationException("Password must contain at least one lowercase letter");
        }

        // Ensure the password contains at least one digit
        if (!value.matches(".*\\d.*")) {
            throw new ValidationException("Password must contain at least one digit");
        }

        // Check if the password matches the defined regex pattern
        if (value.matches(regex)) {
            return;
        }

        // If the password doesn't match the required format, throw an exception
        throw new ValidationException("Password does not meet the required format");
    }
}
