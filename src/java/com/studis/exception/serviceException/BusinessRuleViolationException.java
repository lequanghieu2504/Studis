package com.studis.exception.serviceException;

/**
 * Custom exception for handling cases where a business rule is violated.
 * This exception is thrown when the business logic in the application is violated,
 * such as when certain constraints or conditions are not met.
 */
public class BusinessRuleViolationException extends RuntimeException {

    /**
     * Constructs a new BusinessRuleViolationException with the specified detail message.
     * 
     * @param message The detail message providing more context about the violation.
     */
    public BusinessRuleViolationException(String message) {
        super(message);  // Passes the message to the superclass constructor
    }

    /**
     * Constructs a new BusinessRuleViolationException with the specified detail message and cause.
     * 
     * @param message The detail message providing more context about the violation.
     * @param cause The cause of the exception (a throwable object).
     */
    public BusinessRuleViolationException(String message, Throwable cause) {
        super(message, cause);  // Passes both the message and the cause to the superclass constructor
    }
}
