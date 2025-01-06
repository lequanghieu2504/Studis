package com.studis.utils.validatorUtils;

import com.studis.model.dto.PasswordModificationDTO;

import org.springframework.stereotype.Component;

/**
 * This class validates the password modification request, ensuring:
 * - The email provided is valid.
 * - The new password meets strength requirements.
 * - The confirmation password matches the new password.
 */
@Component
public class UpdatePasswordValidator {

    // Validators for email and password
    private NameOrEmailValidator noev = new NameOrEmailValidator();
    private PasswordValidator pv = new PasswordValidator();

    /**
     * Validates the PasswordModificationDTO object, ensuring the email and passwords are valid and match.
     * 
     * @param pdto The password modification data transfer object.
     * @throws ValidationException If validation fails for email or passwords.
     */
    public void validate(PasswordModificationDTO pdto) {
        // Validate the email format
        noev.validate(pdto.getEmail());

        // Validate the new password format
        pv.validate(pdto.getPassword());

        // Ensure the confirmation password matches the new password
        pv.validate(pdto.getConfirmPassword());
    }

    /**
     * Validates the email in the PasswordModificationDTO object.
     * 
     * @param pdto The password modification data transfer object.
     * @throws ValidationException If the email is invalid.
     */
    public void validateEmail(PasswordModificationDTO pdto) {
        // Validate the email format only (used when the password itself is not validated)
        noev.validate(pdto.getEmail());
    }
}
