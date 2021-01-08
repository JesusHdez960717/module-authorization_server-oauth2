/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.root101.module.authorization_server.oauth2.client;

import com.root101.clean.core.domain.services.ResourceHandler;
import com.root101.module.authorization_server.oauth2.service.ResourceKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Autowired
    private ClientDetailServiceAdapter clientResolver;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        try {
            return clientResolver.convert(clientResolver.loadClientByClientId(clientId));
        } catch (Exception e) {
            throw new UsernameNotFoundException(
                    ResourceHandler.getString(ResourceKeys.KEY_MSG_NO_CLIENT_FOR_NAME)
                    + ": " + clientId);
        }
    }

}
