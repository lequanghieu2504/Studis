package com.studis.service.dao;

import com.studis.exception.daoException.DatabaseConnectionException;
import com.studis.exception.serviceException.TransactionException;
import com.studis.utils.databaseUtils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Base class for DAO services, responsible for handling database connections,
 * transactions (begin, commit, rollback), and connection management.
 * This abstract class provides methods for dealing with database operations in a transaction context.
 * Subclasses of this class can utilize these methods to work with the database.
 */
public abstract class BaseDAOService {

    // ThreadLocal variable to hold a connection for each thread
    protected static ThreadLocal<Connection> threadConnection = new ThreadLocal<>();

    /**
     * Gets the ThreadLocal connection.
     * @return ThreadLocal connection instance
     */
    public static ThreadLocal<Connection> getThreadConnection() {
        return threadConnection;
    }

    /**
     * Retrieves the connection for the current thread. If no connection is present,
     * it creates a new one and associates it with the current thread.
     * @return Connection instance for the current thread
     * @throws DatabaseConnectionException if the connection cannot be established
     */
    protected Connection getConnection() {
        try {
            if (threadConnection.get() == null) {
                Connection con = ConnectionManager.getConnection();  // Get a connection from the connection manager
                if (con == null) {
                    throw new DatabaseConnectionException("Failed to obtain a valid database connection.");
                }
                con.setAutoCommit(false);  // Disable auto-commit for transaction management
                threadConnection.set(con); // Store the connection in ThreadLocal
            }
            return threadConnection.get();  // Return the connection for the current thread
        } catch (SQLException e) {
            throw new DatabaseConnectionException(
                "Error while setting up the database connection. Possible issues: incorrect configuration, network problems, or resource exhaustion.",
                e);
        }
    }

    /**
     * Begins a transaction by ensuring that a valid connection is available.
     * @throws DatabaseConnectionException if connection cannot be established
     */
    protected void beginTransaction() {
        try {
            getConnection();  // Retrieve the connection, implicitly begins a transaction
        } catch (DatabaseConnectionException e) {
            throw new DatabaseConnectionException(
                "Failed to begin transaction due to connection issues. Check database availability and connection settings.", 
                e);
        }
    }

    /**
     * Commits the current transaction. This will apply all changes made during the transaction.
     * @throws TransactionException if committing the transaction fails
     */
    protected void commitTransaction() {
        Connection con = threadConnection.get();
        try {
            if (con != null) {
                con.commit();  // Commit the transaction
            }
        } catch (SQLException e) {
            throw new TransactionException(
                "Commit failed. This might be caused by concurrent modifications, constraint violations, or database system errors.",
                e);
        }
    }

    /**
     * Rolls back the current transaction, undoing any changes made during the transaction.
     * @throws TransactionException if rolling back the transaction fails
     */
    protected void rollbackTransaction() {
        Connection con = threadConnection.get();
        try {
            if (con != null) {
                con.rollback();  // Rollback the transaction
            }
        } catch (SQLException e) {
            throw new TransactionException(
                "Rollback failed. Check if the database is still reachable and whether the connection is in a valid state.",
                e);
        }
    }

    /**
     * Closes the current database connection and removes it from the ThreadLocal storage.
     * @throws TransactionException if closing the connection fails
     */
    protected void closeConnection() {
        Connection con = threadConnection.get();
        try {
            if (con != null) {
                con.close();  // Close the connection
            }
        } catch (SQLException e) {
            throw new TransactionException(
                "Failed to close the database connection. Potential causes: connection already closed, or system-level issues.",
                e);
        } finally {
            threadConnection.remove();  // Remove the connection from ThreadLocal to prevent memory leaks
        }
    }
}
