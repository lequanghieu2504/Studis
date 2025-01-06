package com.studis.utils.validatorUtils;

/**
 * This is a generic interface that defines the structure for all validators.
 * Implementing classes will provide their own validation logic for specific types.
 * 
 * @param <T> The type of the value to be validated.
 */
public interface Validator<T> {

    /**
     * Validates the provided value of type T.
     * 
     * @param value The value to be validated.
     * @throws ValidationException If the validation fails for the given value.
     */
    public void validate(T value);
}
