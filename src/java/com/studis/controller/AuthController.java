/*
 * This controller handles authentication-related operations such as login, registration, and logout.
 * It leverages services and validators to process requests and manage user sessions.
 */

package com.studis.controller;

import com.studis.service.business.LoginService;
import com.studis.service.business.RegisterService;
import com.studis.model.entity.User;
import com.studis.model.dto.LoginDTO;
import com.studis.model.dto.RegisterDTO;
import com.studis.utils.validatorUtils.AuthValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * AuthController: Manages user authentication processes including login, registration, and logout.
 * It uses session attributes to track the authenticated user and redirects accordingly.
 */
@Controller
@SessionAttributes("user") // Stores the authenticated user in the session.
public class AuthController {

    private LoginService ls; // Handles login business logic
    private RegisterService rs; // Handles registration business logic
    private AuthValidator av; // Validates authentication data

    /**
     * Constructor for AuthController. Injects required services and utilities.
     *
     * @param ls LoginService for login-related operations
     * @param rs RegisterService for registration-related operations
     * @param av AuthValidator for validating login and registration data
     */
    @Autowired
    public AuthController(LoginService ls, RegisterService rs, AuthValidator av) {
        this.ls = ls;
        this.rs = rs;
        this.av = av;
    }
    
    /**
     * Handles user login requests.
     *
     * @param nameOrEmail the username or email provided by the user
     * @param password the user's password
     * @param model the model to store attributes for the view
     * @return a redirect to the home page if login is successful
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String handleLogin(
            @RequestParam("nameOrEmail") String nameOrEmail, 
            @RequestParam("password") String password,
            Model model) {

        // Create a DTO object to encapsulate login data
        LoginDTO ldto = new LoginDTO(nameOrEmail, password);
        
        // Validate login data
        av.validate(ldto);

        // Perform login operation and retrieve the authenticated user
        User user = ls.login(ldto.getNameOrEmail(), ldto.getPassword());
        
        // Store the user in the session
        model.addAttribute("user", user);

        // Redirect to the home page after successful login
        return "redirect:/home";
    }

    /**
     * Handles user registration requests.
     *
     * @param name the user's name
     * @param email the user's email
     * @param password the user's password
     * @param confirmPassword the confirmation of the password
     * @return the registration page view name
     */
    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    public String handleRegister(
            @RequestParam("name") String name, 
            @RequestParam("email") String email, 
            @RequestParam("password") String password, 
            @RequestParam("confirmPassword") String confirmPassword) {

        // Create a DTO object to encapsulate registration data
        RegisterDTO rdto = new RegisterDTO(name, email, password, confirmPassword);
        
        // Validate registration data
        av.validate(rdto);

        // Perform the registration operation
        rs.register(rdto.getName(), rdto.getEmail(), rdto.getPassword(), rdto.getConfirmPassword());

        // Return the registration view
        return "register";
    }

    /**
     * Handles user logout requests.
     *
     * @param model the model to remove session attributes
     * @return a redirect to the home page after logout
     */
    @RequestMapping(value = "/doLogout", method = RequestMethod.POST)
    public String handleLogout(Model model) {
        // Remove the user attribute from the session
        model.asMap().remove("user");

        // Redirect to the home page
        return "redirect:/";
    }
}
