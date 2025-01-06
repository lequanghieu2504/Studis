package com.studis.exception.serviceException;

/**
 * Custom exception to represent errors that occur during a transaction.
 * This exception is typically thrown when there are issues related to 
 * database transactions, such as failure to commit or rollback.
 */
public class TransactionException extends RuntimeException {

    /**
     * Constructs a new TransactionException with the specified detail message.
     * 
     * @param message The detail message explaining the reason for the exception.
     */
    public TransactionException(String message) {
        super(message);  // Passes the message to the superclass constructor
    }

    /**
     * Constructs a new TransactionException with the specified detail message and cause.
     * 
     * @param message The detail message explaining the reason for the exception.
     * @param cause The cause of the exception (a throwable object).
     */
    public TransactionException(String message, Throwable cause) {
        super(message, cause);  // Passes both the message and the cause to the superclass constructor
    }
}
