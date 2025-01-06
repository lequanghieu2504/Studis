/*
 * Global exception handler for handling various exceptions in a centralized way.
 * This class uses Spring's @ControllerAdvice to provide exception handling across all controllers.
 */

package com.studis.controller;

import com.studis.exception.commonException.ValidationException;
import com.studis.exception.daoException.DataAccessException;
import com.studis.exception.daoException.DatabaseConnectionException;
import com.studis.exception.daoException.EntityNotFoundException;
import com.studis.exception.serviceException.BusinessRuleViolationException;
import com.studis.exception.serviceException.ServiceException;
import com.studis.exception.serviceException.TransactionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.portlet.ModelAndView;

/**
 * GlobalExceptionHandler: Centralized error handling for exceptions thrown by controllers.
 * Provides user-friendly error messages and sets appropriate HTTP response statuses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions related to data access failures.
     *
     * @param e       the DataAccessException
     * @param request the current HTTP request
     * @return ModelAndView directing to an error view
     */
    @ExceptionHandler(DataAccessException.class)
    public ModelAndView handleDataAccessException(DataAccessException e, HttpServletRequest request) {
        request.setAttribute("status", HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Set HTTP 500 status
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("message", "An error occurred while accessing the data."); // User-friendly message
        return mv;
    }

    /**
     * Handles exceptions when the database connection fails.
     *
     * @param e       the DatabaseConnectionException
     * @param request the current HTTP request
     * @return ModelAndView directing to an error view
     */
    @ExceptionHandler(DatabaseConnectionException.class)
    public ModelAndView handleDatabaseConnectionException(DatabaseConnectionException e, HttpServletRequest request) {
        request.setAttribute("status", HttpServletResponse.SC_SERVICE_UNAVAILABLE); // Set HTTP 503 status
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("message", "The database is currently unavailable. Please try again later.");
        return mv;
    }

    /**
     * Handles cases where the requested entity is not found.
     *
     * @param e       the EntityNotFoundException
     * @param request the current HTTP request
     * @return ModelAndView directing to an error view
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView handleEntityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {
        request.setAttribute("status", HttpServletResponse.SC_NOT_FOUND); // Set HTTP 404 status
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("message", e.getMessage()); // Pass the exception message to the view
        return mv;
    }

    /**
     * Handles business rule violations.
     *
     * @param e       the BusinessRuleViolationException
     * @param request the current HTTP request
     * @return ModelAndView directing to an error view
     */
    @ExceptionHandler(BusinessRuleViolationException.class)
    public ModelAndView handleBusinessRuleViolationException(BusinessRuleViolationException e, HttpServletRequest request) {
        request.setAttribute("status", HttpServletResponse.SC_BAD_REQUEST); // Set HTTP 400 status
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("message", e.getMessage());
        return mv;
    }

    /**
     * Handles transaction-related errors.
     *
     * @param e       the TransactionException
     * @param request the current HTTP request
     * @return ModelAndView directing to an error view
     */
    @ExceptionHandler(TransactionException.class)
    public ModelAndView handleTransactionException(TransactionException e, HttpServletRequest request) {
        request.setAttribute("status", HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Set HTTP 500 status
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("message", "Transaction failed. Please try again later.");
        return mv;
    }

    /**
     * Handles generic service-level exceptions.
     *
     * @param e       the ServiceException
     * @param request the current HTTP request
     * @return ModelAndView directing to an error view
     */
    @ExceptionHandler(ServiceException.class)
    public ModelAndView handleServiceException(ServiceException e, HttpServletRequest request) {
        request.setAttribute("status", HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Set HTTP 500 status
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("message", "A service-related error occurred. Contact support if the issue persists.");
        return mv;
    }

    /**
     * Handles validation errors.
     *
     * @param e       the ValidationException
     * @param request the current HTTP request
     * @return ModelAndView directing to an error view
     */
    @ExceptionHandler(ValidationException.class)
    public ModelAndView handleValidationException(ValidationException e, HttpServletRequest request) {
        request.setAttribute("status", HttpServletResponse.SC_BAD_REQUEST); // Set HTTP 400 status
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("message", "Invalid data format. Please check your input.");
        if (e.getMessage() != null) {
            mv.addObject("details", e.getMessage()); // Include additional details if available
        }
        return mv;
    }

    /**
     * Handles all other exceptions that are not explicitly handled by other methods.
     *
     * @param e       the generic Exception
     * @param request the current HTTP request
     * @return ModelAndView directing to an error view
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneralException(Exception e, HttpServletRequest request) {
        request.setAttribute("status", HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Set HTTP 500 status
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("message", "An unexpected error occurred. Please try again later.");
        return mv;
    }
}
