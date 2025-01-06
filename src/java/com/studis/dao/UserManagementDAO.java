package com.studis.dao;

import com.studis.exception.daoException.DataAccessException;
import com.studis.exception.daoException.DatabaseConnectionException;
import com.studis.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;

/**
 * UserManagementDAO is responsible for managing database operations related to the User entity.
 * It extends BaseDAO to leverage reusable database connection and execution methods.
 */
@Repository
public class UserManagementDAO extends BaseDAO {

    /**
     * Inserts a new User into the database.
     *
     * @param user The User object containing the data to be inserted.
     * @throws DataAccessException if no rows are affected by the operation.
     * @throws DatabaseConnectionException if an error occurs while connecting to the database or executing the query.
     */
    public void create(User user) {
        // SQL query to insert a new user into the 'users' table
        String sql = "INSERT INTO users(user_name, user_email, hashed_password, salt, create_date, recent_access) "
                + "VALUES(?, ?, ?, ?, ?, ?);";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            // Execute the update with user data
            int rowsAffected = executeUpdate(
                    stmt,
                    user.getName(),            // Bind user_name
                    user.getEmail(),           // Bind user_email
                    user.getHashedPassword(),  // Bind hashed_password
                    user.getSalt(),            // Bind salt
                    user.getCreateDate(),      // Bind create_date
                    user.getRecentAccess()     // Bind recent_access
            );

            // Throw an exception if no rows are affected (i.e., insertion failed)
            if (rowsAffected == 0) {
                throw new DataAccessException(
                        "Failed to create user: No rows affected. User data: "
                        + user.getName() + ", " + user.getEmail()
                );
            }
        } catch (SQLException e) {
            // Wrap SQLException in a custom exception for better error handling
            throw new DatabaseConnectionException(
                    "Error while inserting user into database. SQL: " + sql
                    + ", User: " + user.getName() + ", " + user.getEmail(),
                    e
            );
        }
    }

    /**
     * Deletes a User from the database based on the user ID.
     *
     * @param id The ID of the user to be deleted.
     * @throws DataAccessException if no rows are affected by the operation (i.e., user does not exist).
     * @throws DatabaseConnectionException if an error occurs while connecting to the database or executing the query.
     */
    public void delete(int id) {
        // SQL query to delete a user by ID
        String sql = "DELETE FROM users WHERE id = ?;";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            // Execute the update with the provided ID
            int rowsAffected = executeUpdate(stmt, id);

            // Throw an exception if no rows are affected (i.e., deletion failed)
            if (rowsAffected == 0) {
                throw new DataAccessException(
                        "Failed to delete user with id: " + id + ". No rows affected."
                );
            }
        } catch (SQLException e) {
            // Wrap SQLException in a custom exception for better error handling
            throw new DatabaseConnectionException(
                    "Error while deleting user with id: " + id + ". SQL: " + sql,
                    e
            );
        }
    }
}
