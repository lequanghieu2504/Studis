/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studis.utils.validatorUtils;

import com.studis.exception.commonException.ValidationException;

/**
 *
 * @author ho huy
 */
public interface Validator<T> {
    public void validate(T value) 
            throws ValidationException;
}
