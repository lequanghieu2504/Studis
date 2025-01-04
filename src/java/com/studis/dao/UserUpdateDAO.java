package com.studis.dao;

import com.studis.exception.daoException.DataAccessException;
import com.studis.exception.daoException.DatabaseConnectionException;
import com.studis.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserUpdateDAO extends BaseDAO{

    public void update(User user)
            throws DataAccessException,
            DatabaseConnectionException {
        String sql = "update users set "
                + "user_name = ?, "
                + "user_email = ?, "
                + "hashed_password = ?, "
                + "salt = ?, "
                + "recent_access = ? "
                + "where id = ?;";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)){
            int rowsAffected = executeUpdate(stmt,
                    user.getName(),
                    user.getEmail(),
                    user.getHashedPassword(),
                    user.getSalt(),
                    user.getRecentAccess(),
                    user.getId());

            if (rowsAffected == 0) {
                throw new DataAccessException("Failed to update user. No rows affected.");
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error while updating user", e);
        }
    }

}
