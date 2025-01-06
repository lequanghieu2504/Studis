package com.studis.exception.daoException;

/**
 * Custom exception for handling cases where an entity is not found in the database.
 * This exception is thrown when an attempt to retrieve an entity from the database fails
 * because the entity does not exist.
 */
public class EntityNotFoundException extends RuntimeException {

    /**
     * Constructs a new EntityNotFoundException with the specified detail message.
     * 
     * @param message The detail message providing more context about the error.
     */
    public EntityNotFoundException(String message) {
        super(message);  // Passes the message to the superclass constructor
    }

    /**
     * Constructs a new EntityNotFoundException with the specified detail message and cause.
     * 
     * @param message The detail message providing more context about the error.
     * @param cause The cause of the exception (a throwable object).
     */
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);  // Passes both the message and the cause to the superclass constructor
    }
}
