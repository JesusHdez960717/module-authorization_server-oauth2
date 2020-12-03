/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.authorization_server.oauth2.user;

import static com.jhw.module.authorization_server.oauth2.user.ApplicationUserRole.*;
import java.util.Arrays;
import java.util.List;
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

    private final List<UserDetails> users;

    @Autowired
    public UserDetailServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.users = createUsers();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream().filter((UserDetails t) -> {
            return t.getUsername().equals(username);
        }).findFirst().orElseThrow(()
                -> new UsernameNotFoundException("El usuario no existe")
        );
    }

    private List<UserDetails> createUsers() {
        return Arrays.asList(
                User.builder()
                        .username("chicho")
                        .password(passwordEncoder.encode("123"))
                        .authorities("read")
                        .roles(STUDENT.name()) //ROLE_STUDENT
                        .build(),
                User
                        .builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .authorities("read")
                        .roles(ADMIN.name()) //ROLE_ADMIN
                        .build(),
                User
                        .builder()
                        .username("tom")
                        .password(passwordEncoder.encode("tom"))
                        .authorities("read")
                        .roles(ADMIN_TRAINEE.name()) //ROLE_ADMIN_TRAINEE
                        .build());
    }

}
