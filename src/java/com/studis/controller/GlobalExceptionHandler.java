/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studis.controller;

import com.studis.exception.commonException.ValidationException;
import com.studis.exception.daoException.DataAccessException;
import com.studis.exception.daoException.DatabaseConnectionException;
import com.studis.exception.daoException.EntityNotFoundException;
import com.studis.exception.serviceException.BusinessRuleViolationException;
import com.studis.exception.serviceException.ServiceException;
import com.studis.exception.serviceException.TransactionException;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ho huy
 */
public class GlobalExceptionHandler {

    public static void handle(Exception e, String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int status;
        String message;

        if (e instanceof ValidationException) {
            status = HttpServletResponse.SC_BAD_REQUEST;
            message = "Validation failed. Please check the input data.";
        } else if (e instanceof TransactionException) {
            status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            message = "Transaction failed. Please try again later.";
        } else if (e instanceof ServiceException) {
            status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            message = "A service-related error occurred. Contact support if the issue persists.";
        } else if (e instanceof BusinessRuleViolationException) {
            status = HttpServletResponse.SC_BAD_REQUEST;
            message = e.getMessage();
        } else if (e instanceof DatabaseConnectionException) {
            status = HttpServletResponse.SC_SERVICE_UNAVAILABLE;
            message = "The database is currently unavailable. Please try again later.";
        } else if (e instanceof DataAccessException) {
            status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            message = "An error occurred while accessing the data. Please contact support.";
        } else if (e instanceof EntityNotFoundException) {
            status = HttpServletResponse.SC_NOT_FOUND;
            message = e.getMessage();
        } else {
            status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            message = "An unexpected error occurred. Please try again later.";
        }

        request.setAttribute("status", status);
        request.setAttribute("message", message);
        request.getRequestDispatcher(path).forward(request, response);
    }

}
