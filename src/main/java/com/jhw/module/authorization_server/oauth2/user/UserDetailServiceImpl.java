/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.authorization_server.oauth2.user;

import com.root101.clean.core.domain.services.ResourceHandler;
import com.jhw.module.authorization_server.oauth2.service.ResourceKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
@Service
@Primary//marcarlo como primario para que sobreescriba el por defecto
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailServiceAdapter userResolver;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userResolver.convert(userResolver.loadUserByUsername(username));
        } catch (Exception e) {
            throw new UsernameNotFoundException(
                    ResourceHandler.getString(ResourceKeys.KEY_MSG_NO_USER_FOR_USERNAME)
                    + ": " + username);
        }
    }
}
