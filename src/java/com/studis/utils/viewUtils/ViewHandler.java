/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studis.utils.viewUtils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ho huy
 */
public class ViewHandler {
    
    public static void forward(String path, HttpServletRequest request, HttpServletResponse response)
            throws ServletException,
            IOException{
        request.getRequestDispatcher(path).forward(request, response);
    }
    
    public static void forwardWithMessage(String message, String path, HttpServletRequest request, HttpServletResponse response)
            throws ServletException,
            IOException{
        
        request.setAttribute("message", message);
        request.getRequestDispatcher(path).forward(request, response);
    }
}
