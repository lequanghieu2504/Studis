/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studis.utils.validatorUtils;

import com.studis.model.dto.LoginDTO;
import com.studis.model.dto.RegisterDTO;
import com.studis.exception.commonException.ValidationException;

/**
 *
 * @author ho huy
 */
public class AuthValidator {
    
    private NameOrEmailValidator noev = new NameOrEmailValidator();
    private PasswordValidator pv = new PasswordValidator();
    
    public void validate(LoginDTO ldto) 
            throws ValidationException{
        noev.validate(ldto.getNameOrEmail());
        pv.validate(ldto.getPassword());
    }
    
    public void validate(RegisterDTO rdto)
            throws ValidationException{
        noev.validate(rdto.getName());
        noev.validate(rdto.getEmail());
        pv.validate(rdto.getPassword());
        pv.validate(rdto.getConfirmPassword());
    }
}
