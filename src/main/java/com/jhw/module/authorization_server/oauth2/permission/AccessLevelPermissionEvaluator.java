/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.authorization_server.oauth2.permission;

import java.io.Serializable;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class AccessLevelPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication auth, Object targetObject, Object permission) {
        System.out.println(targetObject);
        System.out.println(permission);
        return false;
    }

    @Override
    public boolean hasPermission(Authentication auth, Serializable targuetID, String type, Object permission) {
        throw new UnsupportedOperationException("No implementado");
    }

}
