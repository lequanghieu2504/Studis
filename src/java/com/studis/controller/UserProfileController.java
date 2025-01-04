/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studis.controller;

import com.studis.service.businessService.PasswordModificationService;
import com.studis.service.daoService.UserRetrievalService;
import com.studis.model.dto.PasswordModificationDTO;
import com.studis.utils.validatorUtils.UpdatePasswordValidator;
import com.studis.utils.viewUtils.ViewHandler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ho huy
 */
public class UserProfileController {

    private UserRetrievalService urs = new UserRetrievalService();
    private PasswordModificationService pms = new PasswordModificationService();
    private UpdatePasswordValidator upv = new UpdatePasswordValidator();

    public void handleCofirmEmail(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, 
                IOException {
        try {
            PasswordModificationDTO pdto = new PasswordModificationDTO(request.getParameter("confirmEmail"), null, null);
            upv.validateEmail(pdto);
            urs.handleExist("user_email", pdto.getEmail());
            
            request.setAttribute("confirmEmail", pdto.getEmail());
            ViewHandler.forward("/public/update-password.jsp", request, response);
        } catch (Exception e) {
            GlobalExceptionHandler.handle(e, "index.jsp", request, response);
        }
    }
    
    public void handleUpdatePassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, 
                IOException{
        try {
            PasswordModificationDTO pdto = new PasswordModificationDTO(
                    request.getParameter("confirmEmail"), 
                    request.getParameter("password"), 
                    request.getParameter("confirmPassword"));
            
            upv.validate(pdto);
            
            pms.updatePassword(pdto.getEmail(), pdto.getPassword(), pdto.getConfirmPassword());
            request.getRequestDispatcher("/public/login.jsp").forward(request, response);
        } catch (Exception e) {
            GlobalExceptionHandler.handle(e, "index.jsp", request, response);
        }   
    }
}
