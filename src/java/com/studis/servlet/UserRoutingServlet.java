/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studis.servlet;

import com.studis.utils.viewUtils.ViewHandler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ho huy
 */

@WebServlet({"/user/pageRedirect", "/user/logout"})
public class UserRoutingServlet extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,
            IOException{
        String path = request.getServletPath();
        System.out.println(path);
        if(path.endsWith("/pageRedirect")){
            ViewHandler.forward("/pageRedirect", request, response);
        }else if(path.endsWith("/logout")){
            ViewHandler.forward("/logout", request, response);
        }else{
            ViewHandler.forward("index.jsp", request, response);
        }
    }
}
