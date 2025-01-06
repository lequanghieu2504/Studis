/*
 * BaseDAO: Abstract DAO class providing reusable methods for database operations.
 * This class manages database connections and provides utility methods for executing queries.
 */

package com.studis.dao;

import com.studis.exception.daoException.DataAccessException;
import com.studis.exception.daoException.DatabaseConnectionException;
import com.studis.service.dao.BaseDAOService;
import com.studis.utils.databaseUtils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Abstract DAO class to handle database operations such as executing queries and managing connections.
 */
public abstract class BaseDAO {

    // ThreadLocal to manage database connections for each thread
    ThreadLocal<Connection> threadConnection = BaseDAOService.getThreadConnection();

    /**
     * Gets the database connection for the current thread.
     * If no connection exists, it establishes a new one and associates it with the thread.
     *
     * @return Connection object for the current thread
     * @throws DatabaseConnectionException if a connection cannot be established
     */
    protected Connection getConnection() {
        try {
            if (threadConnection.get() == null) { // Check if the thread has an associated connection
                Connection con = ConnectionManager.getConnection(); // Obtain a new connection
                if (con == null) {
                    throw new SQLException("Database connection could not be established.");
                }
                con.setAutoCommit(false); // Disable auto-commit for transaction management
                threadConnection.set(con); // Associate the connection with the current thread
            }
            return threadConnection.get();
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to establish connection to the database. " + e.getMessage(), e);
        }
    }

    /**
     * Sets parameters for a prepared statement.
     *
     * @param stmt   PreparedStatement to set parameters for
     * @param params Parameters to be set in the statement
     * @throws SQLException if an error occurs while setting parameters
     */
    private void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]); // Index starts at 1 for PreparedStatement
        }
    }

    /**
     * Executes a query and returns the result set.
     *
     * @param stmt   PreparedStatement to execute
     * @param params Parameters for the query
     * @return ResultSet of the executed query
     * @throws DataAccessException if an error occurs during query execution
     */
    public ResultSet executeQuery(PreparedStatement stmt, Object... params) {
        try {
            setParameters(stmt, params); // Set parameters for the query
            return stmt.executeQuery(); // Execute the query and return the result
        } catch (SQLException e) {
            String query = stmt.toString();
            throw new DataAccessException(
                "Error executing query: " + query + " with parameters: " + Arrays.toString(params) +
                " - Error message: " + e.getMessage(), e
            );
        }
    }

    /**
     * Executes an update operation (INSERT, UPDATE, DELETE) and returns the number of affected rows.
     *
     * @param stmt   PreparedStatement to execute
     * @param params Parameters for the query
     * @return Number of rows affected by the query
     * @throws DataAccessException if an error occurs during query execution
     */
    public int executeUpdate(PreparedStatement stmt, Object... params) {
        try {
            setParameters(stmt, params); // Set parameters for the update operation
            return stmt.executeUpdate(); // Execute the update and return the affected rows
        } catch (SQLException e) {
            String query = stmt.toString();
            throw new DataAccessException(
                "Error executing query: " + query + " with parameters: " + Arrays.toString(params) +
                " - Error message: " + e.getMessage(), e
            );
        }
    }

    /**
     * Closes the given ResultSet.
     *
     * @param rs ResultSet to be closed
     * @throws DataAccessException if an error occurs while closing the ResultSet
     */
    protected void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close(); // Close the ResultSet
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error closing ResultSet. Error message: " + e.getMessage(), e);
        } finally {
            threadConnection.remove(); // Remove the connection from the thread-local storage
        }
    }
}
