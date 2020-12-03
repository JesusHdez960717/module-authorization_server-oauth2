/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.authorization_server.oauth2.user;

import static com.jhw.module.authorization_server.oauth2.user.ApplicationUserPermission.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public enum ApplicationUserRole {
    STUDENT(new HashSet<>(Arrays.asList(
            STUDENT_READ,
            STUDENT_WRITE
    ))),
    ADMIN(new HashSet<>(Arrays.asList(
            COURSE_READ,
            COURSE_WRITE,
            STUDENT_READ,
            STUDENT_WRITE
    ))),
    ADMIN_TRAINEE(new HashSet<>(Arrays.asList(
            COURSE_READ,
            STUDENT_READ
    )));

    private final Set<ApplicationUserPermission> permissions;

    private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

}
