package com.studis.exception.serviceException;

/**
 * Custom exception for handling invalid input scenarios.
 * This exception is thrown when user input is invalid or does not meet the expected criteria.
 */
public class InvalidInputException extends RuntimeException {

    /**
     * Constructs a new InvalidInputException with the specified detail message.
     * 
     * @param message The detail message providing more context about the invalid input.
     */
    public InvalidInputException(String message) {
        super(message);  // Passes the message to the superclass constructor
    }

    /**
     * Constructs a new InvalidInputException with the specified detail message and cause.
     * 
     * @param message The detail message providing more context about the invalid input.
     * @param cause The cause of the exception (a throwable object).
     */
    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);  // Passes both the message and the cause to the superclass constructor
    }
}
