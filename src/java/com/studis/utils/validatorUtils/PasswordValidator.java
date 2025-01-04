/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studis.utils.validatorUtils;

import com.studis.exception.commonException.ValidationException;

/**
 *
 * @author ho huy
 */
public class PasswordValidator implements Validator<String>{

    private String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
    
    @Override
    public void validate(String value) throws ValidationException {
        if (value == null || value.isEmpty()) {
            throw new ValidationException("Value cannot be null or empty");
        }
        
        if (value.length() < 8) {
            throw new ValidationException("Password must be at least 8 characters long");
        }

        if (!value.matches(".*[A-Z].*")) {
            throw new ValidationException("Password must contain at least one uppercase letter");
        }

        if (!value.matches(".*[a-z].*")) {
            throw new ValidationException("Password must contain at least one lowercase letter");
        }

        if (!value.matches(".*\\d.*")) {
            throw new ValidationException("Password must contain at least one digit");
        }

        if (value.matches(regex)) {
            return;
        }

        throw new ValidationException("Password does not meet the required format");
    }
    
}
