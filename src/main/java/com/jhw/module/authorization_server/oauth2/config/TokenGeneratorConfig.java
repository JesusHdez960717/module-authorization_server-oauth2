/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.authorization_server.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
@Component
public class TokenGeneratorConfig {

    @Bean("jwt-access-token-converter-generator")
    @Qualifier("jwt-access-token-converter-generator")
    @Primary
    public JwtAccessTokenConverter converter() {
        JwtAccessTokenConverter conv = new JwtAccessTokenConverter();
        conv.setSigningKey("secretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecret");
        return conv;
    }

    @Bean("jwt-access-token-store-generator")
    @Qualifier("jwt-access-token-store-generator")
    @Primary
    public TokenStore tokenStore(
            @Autowired
            @Qualifier("jwt-access-token-converter-generator") JwtAccessTokenConverter converter) {
        TokenStore store = new JwtTokenStore(converter);
        return store;
    }
}
