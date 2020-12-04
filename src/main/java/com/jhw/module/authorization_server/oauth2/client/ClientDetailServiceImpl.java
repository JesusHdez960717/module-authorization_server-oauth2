/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.authorization_server.oauth2.client;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
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

    private final List<ClientDetails> clients;

    @Autowired
    public ClientDetailServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.clients = createClients();
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clients.stream().filter((ClientDetails t) -> {
            return t.getClientId().equals(clientId);
        }).findFirst().orElseThrow(()
                -> new ClientRegistrationException("El cliente " + clientId + " no existe.")
        );
    }

    private List<ClientDetails> createClients() {
        return Arrays.asList(
                new ClientDetailsImpl("client1",
                        passwordEncoder.encode("secret1"),
                        "read",
                        "password"
                ),
                new ClientDetailsImpl("client2",
                        passwordEncoder.encode("secret2"),
                        "read",
                        "authorization_code"
                )
        );
    }

}
