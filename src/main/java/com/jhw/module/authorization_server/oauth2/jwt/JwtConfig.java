/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.authorization_server.oauth2.jwt;

import com.nimbusds.jose.shaded.json.JSONArray;
import java.util.Map;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
@Component
public class JwtConfig {

    //TIENE QUE SER LARGA
    //private static final String SECRET = SHA.hash512("secretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecret");
    static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);//llave random, como tiene que ser

    @Bean
    public JwtAccessTokenConverter tokenConverter() {//el que firma el token y lo encripta
        JwtAccessTokenConverter conv = new JwtCustomConverter();
        conv.setSigningKey(new String(SECRET_KEY.getEncoded()));
        return conv;
    }

    @Bean
    public TokenEnhancerChain tokenEnhancerChain(//la cadena que mejoran el token uno a uno
            @Autowired JwtAccessTokenConverter tokenConverter) {
        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenConverter));
        return tokenEnhancerChain;
    }

    @Bean
    public TokenStore tokenStore(//el store
            @Autowired JwtAccessTokenConverter tokenConverter) {
        TokenStore store = new JwtTokenStore(tokenConverter);
        return store;
    }

    @Bean
    public JwtDecoder decoder(@Autowired JwtAccessTokenConverter converter) {//lo que lo decodifica y comprueba si es verdadero
        Map<String, String> keys = converter.getKey();
        String secret = keys.get("value");
        String alg = keys.get("alg");

        SecretKey key = new SecretKeySpec(secret.getBytes(), alg);

        return NimbusJwtDecoder.withSecretKey(key).build();//NimbusJwtDecoder.withPublicKey(RSAPublicKey)
    }

    @Bean
    public JwtAuthenticationConverter authConverter() {//el que convierte las authorities para compararlas con los roles y demas verificaciones
        JwtAuthenticationConverter conv = new JwtAuthenticationConverter();
        conv.setJwtGrantedAuthoritiesConverter(jwt -> {
            JSONArray arr = (JSONArray) jwt.getClaims().get("authorities");
            return arr.stream()
                    .map(String::valueOf)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        });
        return conv;
    }
}
