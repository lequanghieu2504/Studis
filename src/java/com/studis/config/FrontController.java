/*
 * This class serves as the front controller for the application, replacing the traditional web.xml configuration.
 * It initializes the DispatcherServlet and configures mappings for handling HTTP requests.
 */

package com.studis.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * FrontController: Initializes and configures the DispatcherServlet for the application.
 * It specifies the servlet configuration class, root configuration class, and servlet mappings.
 */
public class FrontController extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Specifies the configuration classes for the DispatcherServlet (web context).
     * These classes contain Spring MVC configurations like view resolvers and handler mappings.
     *
     * @return an array of classes containing Spring MVC configurations
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {MvcConfig.class};
    }

    /**
     * Defines the URL patterns that the DispatcherServlet will handle.
     * In this case, it maps all requests ("/") to the DispatcherServlet.
     *
     * @return an array of URL patterns
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * Specifies the root configuration classes (application context) for non-web-related beans.
     * Returning null means no root configuration is defined in this application.
     *
     * @return null (no root configuration classes)
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }
}
