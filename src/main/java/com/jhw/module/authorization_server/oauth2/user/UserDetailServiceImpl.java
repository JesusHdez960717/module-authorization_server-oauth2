/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.authorization_server.oauth2.user;

import com.clean.core.domain.services.Resource;
import com.jhw.module.admin.seguridad.core.domain.UsuarioDomain;
import com.jhw.module.admin.seguridad.core.usecase_def.UsuarioUseCase;
import com.jhw.module.authorization_server.oauth2.A_ModuleOAuth2;
import com.jhw.module.authorization_server.oauth2.service.ResourceKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
@Service
@Primary//marcarlo como primario para que sobreescriba el por defecto
public class UserDetailServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final UsuarioUseCase usuarioUC = A_ModuleOAuth2.usuarioUC;

    @Autowired
    public UserDetailServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return convert(usuarioUC.loadUserByUsername(username));
        } catch (Exception e) {
            throw new UsernameNotFoundException(
                    Resource.getString(ResourceKeys.KEY_MSG_NO_USER_FOR_USERNAME)
                    + ": " + username);
        }
    }

    protected UserDetails convert(UsuarioDomain ususario) {
        return User.builder()
                .username(ususario.getUsername())
                .password(passwordEncoder.encode(ususario.getPassword()))
                .roles(ususario.getRolFk().getNombreRol()) //ROLE_STUDENT
                .build();
    }
}
