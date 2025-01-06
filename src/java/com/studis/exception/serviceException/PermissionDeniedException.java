package com.studis.exception.serviceException;

/**
 * Custom exception for handling permission denial scenarios.
 * This exception is thrown when a user does not have the necessary permissions
 * to perform a specific action.
 */
public class PermissionDeniedException extends RuntimeException {

    /**
     * Constructs a new PermissionDeniedException with the specified detail message.
     * 
     * @param message The detail message explaining the reason for permission denial.
     */
    public PermissionDeniedException(String message) {
        super(message);  // Passes the message to the superclass constructor
    }

    /**
     * Constructs a new PermissionDeniedException with the specified detail message and cause.
     * 
     * @param message The detail message explaining the reason for permission denial.
     * @param cause The cause of the exception (a throwable object).
     */
    public PermissionDeniedException(String message, Throwable cause) {
        super(message, cause);  // Passes both the message and the cause to the superclass constructor
    }
}
