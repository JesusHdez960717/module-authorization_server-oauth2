/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.authorization_server.oauth2.client;

import com.jhw.module.admin.seguridad.core.domain.ClienteDomain;
import com.jhw.module.admin.seguridad.core.usecase_def.ClienteUseCase;
import com.jhw.module.authorization_server.oauth2.A_ModuleOAuth2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Component;

@Component
public class ClientDetailServiceResolver implements ClientDetailServiceAdapter<ClienteDomain> {

    private final PasswordEncoder passwordEncoder;

    private final ClienteUseCase clienteUC = A_ModuleOAuth2.clienteUC;

    @Autowired
    public ClientDetailServiceResolver(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ClienteDomain loadClientByClientId(String clientId) throws Exception {
        return clienteUC.loadClientByName(clientId);
    }

    @Override
    public ClientDetails convert(ClienteDomain client) {
        return ClientDetailsWrapper.from(passwordEncoder, client);
    }

}
