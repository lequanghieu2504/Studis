package com.studis.utils.validatorUtils;

import com.studis.model.dto.LoginDTO;
import com.studis.model.dto.RegisterDTO;

import org.springframework.stereotype.Component;

/**
 * This class is responsible for validating authentication-related inputs.
 * It ensures that login and registration data meet the required criteria (e.g., valid email, password format).
 */
@Component
public class AuthValidator {

    // Validator for validating names or email addresses
    private NameOrEmailValidator noev = new NameOrEmailValidator();
    
    // Validator for validating passwords
    private PasswordValidator pv = new PasswordValidator();

    /**
     * Validates the login data (name or email and password).
     * 
     * @param ldto The LoginDTO object containing the login credentials to validate.
     */
    public void validate(LoginDTO ldto) {
        // Validate the name or email in the login data
        noev.validate(ldto.getNameOrEmail());
        
        // Validate the password in the login data
        pv.validate(ldto.getPassword());
    }

    /**
     * Validates the registration data (name, email, password, and confirm password).
     * 
     * @param rdto The RegisterDTO object containing the registration details to validate.
     */
    public void validate(RegisterDTO rdto) {
        // Validate the name in the registration data
        noev.validate(rdto.getName());
        
        // Validate the email in the registration data
        noev.validate(rdto.getEmail());
        
        // Validate the password in the registration data
        pv.validate(rdto.getPassword());
        
        // Validate the confirm password in the registration data
        pv.validate(rdto.getConfirmPassword());
    }
}
