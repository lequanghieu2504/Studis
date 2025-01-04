/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studis.controller;

import com.studis.service.businessService.LoginService;
import com.studis.service.businessService.RegisterService;
import com.studis.model.entity.User;
import com.studis.model.dto.LoginDTO;
import com.studis.model.dto.RegisterDTO;
import com.studis.utils.validatorUtils.AuthValidator;
import com.studis.utils.viewUtils.ViewHandler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ho huy
 */
public class AuthController {

    private LoginService ls = new LoginService();
    private RegisterService rs = new RegisterService();
    private AuthValidator av = new AuthValidator();

    public void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LoginDTO ldto = new LoginDTO(request.getParameter("nameOrEmail"), request.getParameter("password"));
            av.validate(ldto);

            User user = ls.login(ldto.getNameOrEmail(), ldto.getPassword());
            request.getSession().setAttribute("user", user);

            request.setAttribute("page", "home.jsp");
            ViewHandler.forwardWithMessage("Login success!", "/user/pageRedirect", request, response);
        } catch (ServletException | IOException e) {
            throw e;
        } catch (Exception e) {
            GlobalExceptionHandler.handle(e, "/public/login.jsp", request, response);
        }
    }

    public void handleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            RegisterDTO rdto = new RegisterDTO(
                    request.getParameter("name"),
                    request.getParameter("email"),
                    request.getParameter("password"),
                    request.getParameter("confirmPassword"));
            av.validate(rdto);

            rs.register(rdto.getName(), rdto.getEmail(), rdto.getPassword(), rdto.getConfirmPassword());
            
            ViewHandler.forwardWithMessage("Register success!", "/public/register.jsp", request, response);
        } catch (ServletException | IOException e) {
            throw e;
        } catch (Exception e) {
            GlobalExceptionHandler.handle(e, "/public/register.jsp", request, response);
        }
    }

    public void handleLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("user");
        request.getRequestDispatcher(request.getContextPath() + "index.jsp");
    }

}
