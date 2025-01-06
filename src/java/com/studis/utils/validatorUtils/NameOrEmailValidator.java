package com.studis.utils.validatorUtils;

import com.studis.exception.commonException.ValidationException;

/**
 * This class validates a string input as either a valid email or a name.
 * The validation checks if the input matches a valid email format or, if it's not an email, ensures it has a reasonable length (3 to 300 characters).
 */
public class NameOrEmailValidator implements Validator<String> {

    // Regular expression for validating email format
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    /**
     * Validates the given value as either a valid email address or a valid name.
     * 
     * @param value The string value (email or name) to validate.
     * @throws ValidationException If the value is null, empty, or does not match a valid email or name format.
     */
    @Override
    public void validate(String value) {
        // Check if the value is null or empty
        if (value == null || value.isEmpty()) {
            throw new ValidationException("Value cannot be null or empty");
        }

        // If the value matches the email format, it's valid
        if (value.matches(EMAIL_REGEX)) {
            return;
        }

        // If the value has a length between 3 and 300 characters, it's valid as a name
        if (value.length() >= 3 && value.length() <= 300) {
            return;
        }

        // If the value is neither a valid email nor a valid name, throw an exception
        throw new ValidationException("Input does not match valid email or name format");
    }
}
