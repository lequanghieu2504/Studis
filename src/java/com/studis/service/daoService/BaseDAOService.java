/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studis.service.daoService;

import com.studis.exception.daoException.DatabaseConnectionException;
import com.studis.exception.serviceException.TransactionException;
import com.studis.utils.databaseUtils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author ho huy
 */
public abstract class BaseDAOService {

    protected static ThreadLocal<Connection> threadConnection = new ThreadLocal<>();

    public static ThreadLocal<Connection> getThreadConnection() {
        return threadConnection;
    }

    protected Connection getConnection()
            throws DatabaseConnectionException {
        try {
            if (threadConnection.get() == null) {
                Connection con = ConnectionManager.getConnection();
                if (con == null) {
                    throw new DatabaseConnectionException("Failed to obtain a valid database connection.");
                }
                con.setAutoCommit(false);
                threadConnection.set(con);
            }
            return threadConnection.get();
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error while setting up the database connection.", e);
        }
    }

    protected void beginTransaction()
            throws DatabaseConnectionException {
        try {
            getConnection();
        } catch (DatabaseConnectionException e) {
            throw new DatabaseConnectionException("Failed to begin transaction due to connection issues.", e);
        }
    }

    protected void commitTransaction()
            throws TransactionException {
        Connection con = threadConnection.get();
        try {
            if (con != null) {
                con.commit();
            }
        } catch (SQLException e) {
            throw new TransactionException("Commit failed due to an unexpected error.", e);
        }
    }

    protected void rollbackTransaction()
            throws TransactionException {
        Connection con = threadConnection.get();
        try {
            if (con != null) {
                con.rollback();
            }
        } catch (SQLException e) {
            throw new TransactionException("Rollback failed after an unexpected error.", e);
        }
    }

    protected void closeConnection()
            throws TransactionException {
        Connection con = threadConnection.get();
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            throw new TransactionException("Failed to close the database connection.", e);
        } finally {
            threadConnection.remove();
        }
    }
}
