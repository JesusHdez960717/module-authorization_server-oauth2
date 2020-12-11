/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.authorization_server.oauth2.user;

import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 * @param <UserType>
 */
public interface UserDetailServiceAdapter<UserType> {

    public UserType loadUserByUsername(String username) throws Exception;

    public UserDetails convert(UserType ususario);
}
