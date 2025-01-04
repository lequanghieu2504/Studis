/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studis.dao;

import com.studis.exception.daoException.DataAccessException;
import com.studis.exception.daoException.DatabaseConnectionException;
import com.studis.service.daoService.BaseDAOService;
import com.studis.utils.databaseUtils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ho huy
 */
public abstract class BaseDAO {

    ThreadLocal<Connection> threadConnection = BaseDAOService.getThreadConnection();

    protected Connection getConnection() 
            throws DatabaseConnectionException{
        try {
            if(threadConnection.get() == null){
                Connection con = ConnectionManager.getConnection();
                if(con == null) throw new SQLException();
                con.setAutoCommit(false);
                threadConnection.set(con);
            }
            return threadConnection.get();
        } catch (SQLException e){
            throw new DatabaseConnectionException("");
        }
    }

    private void setParameters(PreparedStatement stmt, Object... params)
            throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }

    public ResultSet executeQuery(PreparedStatement stmt, Object... params)
            throws SQLException{
        setParameters(stmt, params);
        return stmt.executeQuery();
    }

    public Integer executeUpdate(PreparedStatement stmt, Object... params)
            throws SQLException{
        setParameters(stmt, params);
        return stmt.executeUpdate();
    }

    protected void closeResultSet(ResultSet rs)
            throws DataAccessException {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error closing ResultSet", e);
        }
    }
}
