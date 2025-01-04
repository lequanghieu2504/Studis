/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studis.model.dto;

/**
 *
 * @author ho huy
 */
public class LoginDTO {
    private final String nameOrEmail;
    private final String password;

    public LoginDTO(String nameOrEmail, String password) {
        this.nameOrEmail = nameOrEmail;
        this.password = password;
    }

    public String getNameOrEmail() {
        return nameOrEmail;
    }

    public String getPassword() {
        return password;
    }
}
