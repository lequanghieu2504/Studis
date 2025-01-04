package com.studis.dao;

import com.studis.exception.daoException.DataAccessException;
import com.studis.exception.daoException.DatabaseConnectionException;
import com.studis.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserManagementDAO extends BaseDAO{

    public void create(User user)
            throws DataAccessException,
            DatabaseConnectionException {

        String sql = "insert into users(user_name, user_email, hashed_password, salt, create_date, recent_access) "
                + "values(?, ?, ?, ?, ?, ?);";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)){
            int rowsAffected = executeUpdate(
                    stmt,
                    user.getName(),
                    user.getEmail(),
                    user.getHashedPassword(),
                    user.getSalt(),
                    user.getCreateDate(),
                    user.getRecentAccess());
            if (rowsAffected == 0) {
                throw new DataAccessException("Failed to create user. No rows affected.");
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error while inserting user into database", e);
        }
    }

    public void delete(int id)
            throws DataAccessException,
            DatabaseConnectionException {
        String sql = "delete from users where id = ?;";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)){
            int rowsAffected = executeUpdate(stmt, id);
            if (rowsAffected == 0) {
                throw new DataAccessException("Failed to delete user. No rows affected.");
            }
        } catch (SQLException e){
            throw new DatabaseConnectionException("Error while deleting user", e);
        }
    }

}
