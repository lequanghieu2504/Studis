/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studis.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ho huy
 */
public class UserPageRedirectController {
    
    public void handleRedirect(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,
            IOException{
        String page = (String) request.getParameter("page");
        page = page == null? (String) request.getAttribute("page"): page;
        switch (page) {
            case "home.jsp":
            case "user-profile.jsp":
            case "settings.jsp":
                request.getRequestDispatcher("/WEB-INF/user/" + page).forward(request, response);
                break;
            default:
                //throw?
        }
    }
}
