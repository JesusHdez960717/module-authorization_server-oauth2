/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.root101.module.authorization_server.oauth2.client;

import org.springframework.security.oauth2.provider.ClientDetails;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 * @param <ClientType>
 */
public interface ClientDetailServiceAdapter<ClientType> {

    public ClientType loadClientByClientId(String client) throws Exception;

    public ClientDetails convert(ClientType client);
}
