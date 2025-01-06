package com.studis.exception.serviceException;

/**
 * Custom exception to represent errors occurring within the service layer.
 * This exception is typically thrown when a service operation fails due to
 * issues such as business rule violations, invalid inputs, or other service
 * related issues.
 */
public class ServiceException extends RuntimeException {

    /**
     * Constructs a new ServiceException with the specified detail message.
     * 
     * @param message The detail message explaining the reason for the exception.
     */
    public ServiceException(String message) {
        super(message);  // Passes the message to the superclass constructor
    }

    /**
     * Constructs a new ServiceException with the specified detail message and cause.
     * 
     * @param message The detail message explaining the reason for the exception.
     * @param cause The cause of the exception (a throwable object).
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);  // Passes both the message and the cause to the superclass constructor
    }
}
