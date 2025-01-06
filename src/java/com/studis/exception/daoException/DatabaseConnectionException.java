package com.studis.exception.daoException;

/**
 * Custom exception for handling database connection errors.
 * This exception is thrown when an error occurs while trying to establish a connection to the database.
 */
public class DatabaseConnectionException extends RuntimeException {

    /**
     * Constructs a new DatabaseConnectionException with the specified detail message.
     * 
     * @param message The detail message providing more context about the error.
     */
    public DatabaseConnectionException(String message) {
        super(message);  // Passes the message to the superclass constructor
    }

    /**
     * Constructs a new DatabaseConnectionException with the specified detail message and cause.
     * 
     * @param message The detail message providing more context about the error.
     * @param cause The cause of the exception (a throwable object).
     */
    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);  // Passes both the message and the cause to the superclass constructor
    }
}
