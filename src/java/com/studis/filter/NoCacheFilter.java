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
 * The NoCacheFilter prevents browser caching of sensitive pages by setting the
 * appropriate HTTP headers to disable caching. This ensures that the pages
 * always display fresh data and are not cached by the browser.
 * 
 * This filter is applied to the following URLs:
 * - /home
 * - /user-profile
 * - /settings
 * - /doLogout
 */
@WebFilter(urlPatterns = {"/home", "/user-profile", "/settings", "/doLogout"})
public class NoCacheFilter implements Filter {

    /**
     * Sets HTTP headers to disable caching in the browser for the requested pages.
     * This ensures that pages always load fresh content and are not served from
     * the browser cache.
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
        
        // Set the HTTP headers to prevent caching
        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        res.setHeader("Pragma", "no-cache");
        res.setDateHeader("Expires", 0);
        
        // Continue with the filter chain
        chain.doFilter(request, response);
    }
}
