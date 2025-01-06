package com.studis.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The AuthFilter ensures that the user is authenticated before they can access
 * certain pages. If the user is not logged in (i.e., no "user" attribute in 
 * the session), they are redirected to the login page (index.jsp).
 * 
 * This filter is applied to the following URLs:
 * - /home
 * - /user-profile
 * - /settings
 * - /doLogout
 */
@WebFilter(urlPatterns = {"/home", "/user-profile", "/settings", "/doLogout"})
public class AuthFilter implements Filter {

    /**
     * Checks if a user is logged in by verifying the session attribute "user".
     * If not authenticated, the user is redirected to the login page (index.jsp).
     * If authenticated, the request proceeds to the next filter or servlet.
     * 
     * @param request The servlet request.
     * @param response The servlet response.
     * @param chain The filter chain for further request processing.
     * @throws IOException If an input or output error occurs during the filter processing.
     * @throws ServletException If the request handling fails.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        // Check if the user is logged in by looking for the "user" session attribute
        Object user = req.getSession().getAttribute("user");    
        
        // If no user is logged in, redirect to the login page (index.jsp)
        if (user == null) {
            res.sendRedirect("index.jsp");
            return; // Stop further processing
        }
        
        // If the user is logged in, allow the request to proceed
        chain.doFilter(request, response);
    }
}
