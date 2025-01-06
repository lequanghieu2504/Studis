/*
 * This controller handles simple page forwarding for various views in the application.
 * It maps specific HTTP requests to their corresponding JSP view names.
 */

package com.studis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ForwardController: Manages navigation between static or semi-static pages.
 * It provides mappings for login, register, home, user profile, and settings pages.
 */
@Controller
public class ForwardController {

    /**
     * Handles requests to display the login page.
     * 
     * @return the name of the "login" view
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLoginPage() {
        return "login"; // Returns the login.jsp view
    }

    /**
     * Handles requests to display the registration page.
     * 
     * @return the name of the "register" view
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String toRegisterPage() {
        return "register"; // Returns the register.jsp view
    }

    /**
     * Handles GET requests to display the home page.
     * 
     * @return the name of the "home" view
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String toHomePage1() {
        return "home"; // Returns the home.jsp view
    }

    /**
     * Handles POST requests to display the home page.
     * 
     * @return the name of the "home" view
     */
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String toHomePage2() {
        return "home"; // Returns the home.jsp view
    }

    /**
     * Handles POST requests to display the user profile page.
     * 
     * @return the name of the "user-profile" view
     */
    @RequestMapping(value = "/user-profile", method = RequestMethod.POST)
    public String toPage() {
        return "user-profile"; // Returns the user-profile.jsp view
    }

    /**
     * Handles POST requests to display the settings page.
     * 
     * @return the name of the "settings" view
     */
    @RequestMapping(value = "/settings", method = RequestMethod.POST)
    public String toSettingsPage() {
        return "settings"; // Returns the settings.jsp view
    }
}
