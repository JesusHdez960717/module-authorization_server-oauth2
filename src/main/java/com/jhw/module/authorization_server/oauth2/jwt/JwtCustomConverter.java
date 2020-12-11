/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.authorization_server.oauth2.jwt;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * NO ponerlo como @Component porque lo cogería automático y se necesita que lo
 * coja en la configuracion general de JWT para agregarla la llave de cifrado
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class JwtCustomConverter extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Object userInfo = authentication.getPrincipal();

        final Map<String, Object> aditionalInfo = new HashMap<>();
        aditionalInfo.put("buajajaja", "123123123123");
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(aditionalInfo);

        return super.enhance(accessToken, authentication);
    }

}
