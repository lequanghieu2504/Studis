package com.studis.dao;

import com.studis.exception.daoException.DataAccessException;
import com.studis.exception.daoException.DatabaseConnectionException;
import com.studis.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object (DAO) for updating user information in the database.
 * Handles operations related to updating user data.
 */
@Repository
public class UserUpdateDAO extends BaseDAO {

    /**
     * Updates the user information in the database.
     * 
     * @param user The user entity containing the data to be updated.
     * @throws DataAccessException if the update operation fails.
     * @throws DatabaseConnectionException if there is an error establishing a database connection.
     */
    public void update(User user) {

        // SQL query to update the user information in the 'users' table
        String sql = "update users set "
                + "user_name = ?, "
                + "user_email = ?, "
                + "hashed_password = ?, "
                + "salt = ?, "
                + "recent_access = ? "
                + "where id = ?;";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            // Execute the update query with the user data
            int rowsAffected = executeUpdate(stmt,
                    user.getName(),
                    user.getEmail(),
                    user.getHashedPassword(),
                    user.getSalt(),
                    user.getRecentAccess(),
                    user.getId());

            // Check if the update operation affected any rows
            if (rowsAffected == 0) {
                throw new DataAccessException("Failed to update user. No rows affected. User ID: " + user.getId());
            }
        } catch (SQLException e) {
            // Handle SQL errors and throw a DatabaseConnectionException with details
            throw new DatabaseConnectionException(
                    "Error while updating user. SQL: " + sql
                    + ", User ID: " + user.getId()
                    + ", Data: { Name: " + user.getName()
                    + ", Email: " + user.getEmail()
                    + ", Recent Access: " + user.getRecentAccess() + " }",
                    e);
        }
    }
}
