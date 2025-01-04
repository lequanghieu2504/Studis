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
public class NameOrEmailValidator implements Validator<String> {

    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @Override
    public void validate(String value) throws ValidationException {
        if (value == null || value.isEmpty()) {
            throw new ValidationException("Value cannot be null or empty");
        }

        if (value.matches(EMAIL_REGEX)) {
            return;
        }

        if (value.length() >= 3 && value.length() <= 300) {
            return;
        }

        throw new ValidationException("Input does not match valid email or name format");
    }

}
