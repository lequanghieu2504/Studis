package com.studis.dao;

import com.studis.exception.daoException.DatabaseConnectionException;
import com.studis.exception.daoException.EntityNotFoundException;
import com.studis.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;

/**
 * UserRetrievalDAO is responsible for retrieving user data from the database.
 * It provides methods to retrieve users by various criteria such as ID, name, email, or other fields.
 */
@Repository
public class UserRetrievalDAO extends BaseDAO {

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to be retrieved.
     * @return The User object containing the retrieved data.
     * @throws EntityNotFoundException if no user is found with the provided ID.
     * @throws DatabaseConnectionException if an error occurs while executing the SQL query.
     */
    public User retrieve(int id) {
        // SQL query to retrieve a user by ID
        String sql = "SELECT * FROM users WHERE id = ?;";
        ResultSet rs = null;

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            // Execute the query with the given ID
            rs = executeQuery(stmt, id);

            // Check if a user is found and return the user object
            if (rs.next()) {
                return toUser(rs);
            } else {
                // If no user is found, throw EntityNotFoundException
                throw new EntityNotFoundException("User with ID " + id + " not found in database.");
            }
        } catch (SQLException e) {
            // Handle SQLException and wrap it in a custom exception
            throw new DatabaseConnectionException(
                    "Error while executing SQL query for user retrieval by ID. SQL: " + sql + ", ID: " + id, e);
        } finally {
            // Close ResultSet and release resources
            closeResultSet(rs);
        }
    }

    /**
     * Retrieves a user by their username or email.
     *
     * @param nameOrEmail The username or email of the user to be retrieved.
     * @return The User object containing the retrieved data.
     * @throws EntityNotFoundException if no user is found with the provided username or email.
     * @throws DatabaseConnectionException if an error occurs while executing the SQL query.
     */
    public User retrieve(String nameOrEmail) {
        // SQL query to retrieve a user by username or email
        String sql = "SELECT * FROM users WHERE user_name = ? OR user_email = ?;";
        ResultSet rs = null;

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            // Execute the query with the provided username or email
            rs = executeQuery(stmt, nameOrEmail, nameOrEmail);

            // Check if a user is found and return the user object
            if (rs.next()) {
                return toUser(rs);
            } else {
                // If no user is found, throw EntityNotFoundException
                throw new EntityNotFoundException(
                        "User with name or email '" + nameOrEmail + "' not found in database.");
            }
        } catch (SQLException e) {
            // Handle SQLException and wrap it in a custom exception
            throw new DatabaseConnectionException(
                    "Error while executing SQL query for user retrieval by name or email. SQL: " + sql + ", Input: " + nameOrEmail, e);
        } finally {
            // Close ResultSet and release resources
            closeResultSet(rs);
        }
    }

    /**
     * Retrieves a user based on a specified field and its value.
     *
     * @param field The field to search by (e.g., user_email, user_name).
     * @param value The value to search for in the specified field.
     * @param <T> The type of the value (could be String, Integer, etc.).
     * @return The User object containing the retrieved data.
     * @throws EntityNotFoundException if no user is found with the given field value.
     * @throws DatabaseConnectionException if an error occurs while executing the SQL query.
     */
    public <T> User retrieve(String field, T value) {
        // SQL query to retrieve a user based on any field and value
        String sql = "SELECT * FROM users WHERE " + field + " = ?;";
        ResultSet rs = null;

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            // Execute the query with the provided field and value
            rs = executeQuery(stmt, value);

            // Check if a user is found and return the user object
            if (rs.next()) {
                return toUser(rs);
            } else {
                // If no user is found, throw EntityNotFoundException
                throw new EntityNotFoundException(
                        "User with field '" + field + "' containing value '" + value + "' not found in database.");
            }
        } catch (SQLException e) {
            // Handle SQLException and wrap it in a custom exception
            throw new DatabaseConnectionException(
                    "Error while executing SQL query for user retrieval by field. SQL: " + sql + ", Field: " + field + ", Value: " + value, e);
        } finally {
            // Close ResultSet and release resources
            closeResultSet(rs);
        }
    }

    /**
     * Retrieves the ID of a user based on a specified field and its value.
     *
     * @param field The field to search by (e.g., user_email, user_name).
     * @param value The value to search for in the specified field.
     * @param <T> The type of the value (could be String, Integer, etc.).
     * @return The ID of the user.
     */
    public <T> int retrieveId(String field, T value) {
        // Retrieve the user based on the field and value, and return the user ID
        User user = retrieve(field, value);
        return user.getId();
    }

    /**
     * Checks if a user exists in the database based on a specified field and value.
     *
     * @param field The field to search by (e.g., user_email, user_name).
     * @param value The value to search for in the specified field.
     * @param <T> The type of the value (could be String, Integer, etc.).
     * @return True if the user exists, false if the user does not exist.
     */
    public <T> boolean exist(String field, T value) {
        try {
            // Try to retrieve the user based on the field and value
            retrieve(field, value);
            return true;  // If retrieval is successful, the user exists
        } catch (EntityNotFoundException e) {
            // If an exception is thrown, the user does not exist
            return false;
        }
    }

    /**
     * Converts a ResultSet row to a User object.
     *
     * @param rs The ResultSet containing the user data.
     * @return The User object representing the retrieved data.
     * @throws SQLException if an error occurs while accessing the ResultSet.
     */
    private User toUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),                  // Get user ID
                rs.getString("user_name"),         // Get user name
                rs.getString("user_email"),        // Get user email
                rs.getString("hashed_password"),   // Get hashed password
                rs.getString("salt"),              // Get salt
                rs.getDate("create_date")          // Get create date
        );
    }
}
