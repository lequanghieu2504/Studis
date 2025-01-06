package com.studis.exception.daoException;

/**
 * Custom exception for handling data access errors.
 * This exception is thrown when an error occurs during database operations.
 */
public class DataAccessException extends RuntimeException {

    /**
     * Constructs a new DataAccessException with the specified detail message.
     * 
     * @param message The detail message providing more context about the error.
     */
    public DataAccessException(String message) {
        super(message);  // Passes the message to the superclass constructor
    }

    /**
     * Constructs a new DataAccessException with the specified detail message and cause.
     * 
     * @param message The detail message providing more context about the error.
     * @param cause The cause of the exception (a throwable object).
     */
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);  // Passes both the message and the cause to the superclass constructor
    }
}
