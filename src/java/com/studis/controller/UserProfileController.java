/*
 * Controller for handling user profile-related actions, such as email confirmation and password updates.
 * This class manages requests for updating user information and validates input data.
 */

package com.studis.controller;

import com.studis.service.business.PasswordModificationService;
import com.studis.service.dao.UserRetrievalService;
import com.studis.model.dto.PasswordModificationDTO;
import com.studis.utils.validatorUtils.UpdatePasswordValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;

/**
 * UserProfileController: Handles requests related to user email confirmation and password updates.
 */
@Controller
public class UserProfileController {

    private final UserRetrievalService urs; // Service for retrieving user data
    private final PasswordModificationService pms; // Service for updating user passwords
    private final UpdatePasswordValidator upv; // Utility for validating password updates

    /**
     * Constructor for injecting required services and utilities.
     *
     * @param urs UserRetrievalService for checking user existence
     * @param pms PasswordModificationService for password updates
     * @param upv UpdatePasswordValidator for validating input data
     */
    @Autowired
    public UserProfileController(UserRetrievalService urs, PasswordModificationService pms, UpdatePasswordValidator upv) {
        this.urs = urs;
        this.pms = pms;
        this.upv = upv;
    }

    /**
     * Handles email confirmation requests.
     * 
     * @param confirmEmail The email address entered by the user for confirmation
     * @return ModelAndView directing to the "update-password" page with the confirmed email
     */
    @RequestMapping(value = "/confirmEmail", method = RequestMethod.POST)
    public ModelAndView handleConfirmEmail(@RequestParam("confirmEmail") String confirmEmail) {
        // Create a DTO for email confirmation
        PasswordModificationDTO pdto = new PasswordModificationDTO(confirmEmail);

        // Validate the email format
        upv.validateEmail(pdto);

        // Check if the email exists in the database
        urs.handleExist("user_email", pdto.getEmail());

        // Prepare the ModelAndView with the confirmed email
        ModelAndView mv = new ModelAndView();
        mv.addObject("confirmEmail", pdto.getEmail()); // Pass email to the view
        mv.setView("update-password"); // Direct to the update-password page
        return mv;
    }

    /**
     * Handles password update requests.
     * 
     * @param confirmEmail The email address associated with the user account
     * @param password The new password entered by the user
     * @param confirmPassword Confirmation of the new password entered by the user
     * @return Redirects to the "index" page after successful password update
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public String handleUpdatePassword(
            @RequestParam("confirmEmail") String confirmEmail,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword) {
        // Create a DTO for password update
        PasswordModificationDTO pdto = new PasswordModificationDTO(confirmEmail, password, confirmPassword);

        // Validate the input data, including email, password, and confirmation password
        upv.validate(pdto);

        // Update the user's password in the database
        pms.updatePassword(pdto.getEmail(), pdto.getPassword(), pdto.getConfirmPassword());

        // Redirect to the index page after successful update
        return "index";
    }
}
