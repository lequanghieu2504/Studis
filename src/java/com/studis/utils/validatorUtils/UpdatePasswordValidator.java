/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studis.utils.validatorUtils;

import com.studis.model.dto.PasswordModificationDTO;
import com.studis.exception.commonException.ValidationException;


/**
 *
 * @author ho huy
 */
public class UpdatePasswordValidator {
    
    private NameOrEmailValidator noev = new NameOrEmailValidator();
    private PasswordValidator pv = new PasswordValidator();
    
    public void validate(PasswordModificationDTO pdto) throws ValidationException{
        noev.validate(pdto.getEmail());
        pv.validate(pdto.getPassword());
        pv.validate(pdto.getConfirmPassword());
    }
    
    public void validateEmail(PasswordModificationDTO pdto) throws ValidationException{
        noev.validate(pdto.getEmail());
    }
}
