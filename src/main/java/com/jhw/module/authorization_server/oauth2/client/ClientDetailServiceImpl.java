/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.authorization_server.oauth2.client;

import com.clean.core.domain.services.Resource;
import com.jhw.module.admin.seguridad.core.usecase_def.ClienteUseCase;
import com.jhw.module.authorization_server.oauth2.A_ModuleOAuth2;
import com.jhw.module.authorization_server.oauth2.service.ResourceKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
@Service
@Primary//marcarlo como primario para que sobreescriba el por defecto
public class ClientDetailServiceImpl implements ClientDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final ClienteUseCase clienteUC = A_ModuleOAuth2.clienteUC;

    @Autowired
    public ClientDetailServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        try {
            return ClientDetailsWrapper.from(passwordEncoder, clienteUC.loadClientByName(clientId));
        } catch (Exception e) {
            throw new UsernameNotFoundException(
                    Resource.getString(ResourceKeys.KEY_MSG_NO_CLIENT_FOR_NAME)
                    + ": " + clientId);
        }
    }

}
