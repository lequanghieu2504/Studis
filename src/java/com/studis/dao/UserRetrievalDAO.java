/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studis.dao;

import com.studis.exception.daoException.DataAccessException;
import com.studis.exception.daoException.DatabaseConnectionException;
import com.studis.exception.daoException.EntityNotFoundException;
import com.studis.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ho huy
 */
public class UserRetrievalDAO extends BaseDAO {

    public User retrieve(int id)
            throws DataAccessException,
            DatabaseConnectionException,
            EntityNotFoundException {

        String sql = "select * from users where id = ?;";
        ResultSet rs = null;

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)){
            rs = executeQuery(stmt, id);
            if (rs.next()) {
                return toUser(rs);
            } else {
                throw new EntityNotFoundException("User with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error while executing SQL query for user retrieval", e);
        } finally {
            closeResultSet(rs);
        }
    }

    public User retrieve(String nameOrEmail)
            throws DataAccessException,
            DatabaseConnectionException,
            EntityNotFoundException {
        String sql = "select * from users where user_name = ? or user_email = ?;";
        ResultSet rs = null;

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)){
            rs = executeQuery(stmt, nameOrEmail,    nameOrEmail);
            if (rs.next()) {
                return toUser(rs);
            } else {
                throw new EntityNotFoundException("User with name or email " + nameOrEmail + " not found.");
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error while executing SQL query for user retrieval", e);
        } finally {
            closeResultSet(rs);
        }
    }

    public <T> User retrieve(String field, T value)
            throws DataAccessException,
            DatabaseConnectionException,
            EntityNotFoundException {
        String sql = "select * from users where " + field + " = ?;";
        ResultSet rs = null;

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)){
            rs = executeQuery(stmt, value);
            if (rs.next()) {
                return toUser(rs);
            } else {
                throw new EntityNotFoundException("User with field " + field + " containing value " + value + " not found.");
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error while executing SQL query for user retrieval", e);
        } finally {
            closeResultSet(rs);
        }
    }

    public <T> int retrieveId(String field, T value)
            throws DataAccessException,
            DatabaseConnectionException,
            EntityNotFoundException {
        User user = retrieve(field, value);
        return user.getId();
    }

    public <T> boolean exist(String field, T value)
            throws DataAccessException,
            DatabaseConnectionException{
        try {
            User user = retrieve(field, value);
            return true;
        } catch (EntityNotFoundException e){
            return false;
        }
    }

    private User toUser(ResultSet rs)
            throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("user_name"),
                rs.getString("user_email"),
                rs.getString("hashed_password"),
                rs.getString("salt"),
                rs.getDate("create_date")
        );
    }
}
