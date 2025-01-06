/*
 * This class serves as the central configuration for the Spring MVC framework.
 * It enables web MVC functionality, specifies the component scanning package,
 * and configures the view resolver for rendering JSP files.
 */

package com.studis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * MvcConfig: Configures the Spring MVC setup for the application.
 * It includes enabling Web MVC features, scanning components, and setting up a view resolver.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.studis") // Scans the "com.studis" package for Spring-managed components.
public class MvcConfig extends WebMvcConfigurerAdapter {

    /**
     * Configures the InternalResourceViewResolver bean.
     * This resolver maps logical view names to physical JSP files located in the "/WEB-INF/view/" directory.
     * 
     * Example:
     * A view name "home" will be resolved to "/WEB-INF/view/home.jsp".
     *
     * @return an instance of InternalResourceViewResolver
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        
        // Specifies the folder where JSP files are located
        vr.setPrefix("/WEB-INF/view/");
        
        // Specifies the file extension for view files
        vr.setSuffix(".jsp");
        
        return vr;
    }
}
