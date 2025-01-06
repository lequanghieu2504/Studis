package com.studis.exception.commonException;

/**
 * Custom exception to handle validation errors.
 * This exception is thrown when validation checks fail.
 */
public class ValidationException extends RuntimeException {

    /**
     * Constructs a new ValidationException with the specified detail message.
     * 
     * @param message The detail message explaining the validation error.
     */
    public ValidationException(String message) {
        super(message);  // Passes the message to the superclass constructor
    }
}
