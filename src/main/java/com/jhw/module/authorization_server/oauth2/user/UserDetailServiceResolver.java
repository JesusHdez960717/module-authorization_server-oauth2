/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.authorization_server.oauth2.user;

import com.jhw.module.admin.seguridad.core.domain.UsuarioDomain;
import com.jhw.module.admin.seguridad.core.usecase_def.UsuarioUseCase;
import com.jhw.module.authorization_server.oauth2.A_ModuleOAuth2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceResolver implements UserDetailServiceAdapter<UsuarioDomain> {

    private final PasswordEncoder passwordEncoder;

    private final UsuarioUseCase usuarioUC = A_ModuleOAuth2.usuarioUC;

    @Autowired
    public UserDetailServiceResolver(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioDomain loadUserByUsername(String username) throws Exception {
        return usuarioUC.loadUserByUsername(username);
    }

    @Override
    public UserDetails convert(UsuarioDomain ususario) {
        return User.builder()
                .username(ususario.getUsername())
                .password(passwordEncoder.encode(ususario.getPublicPassword()))
                .roles(ususario.getRolFk().getNombreRol())
                .build();
    }

}
