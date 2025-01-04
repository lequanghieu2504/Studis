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

@WebServlet({"/public/login", "/public/register", "/public/updateEmail", "/public/confirmEmail"})
public class PublicRoutingServlet extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,
            IOException{
        
        String path = request.getServletPath();
        if(path.endsWith("/login")){
            ViewHandler.forward("/login", request, response);
        }else if(path.endsWith("/register")){
            ViewHandler.forward("/register", request, response);
        }else if(path.endsWith("/confirmEmail")){
            ViewHandler.forward("/confirmEmail", request, response);
        }else if(path.endsWith("/updatePassword")){
            ViewHandler.forward("/updatePassword", request, response);
        }else{
            //
        }
        
    }
    
}
