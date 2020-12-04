/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.authorization_server.oauth2.client;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class ClientDetailsImpl implements ClientDetails {

    private String clientID;
    private String secret;
    private String scope;
    private String grantType;
    private String redirecURI = "http://localhost:9090";
    private int tokenValidationSeconds = 43200;
    private int refreshTokenValidationSeconds = 43200;

    public ClientDetailsImpl(String clientID, String secret, String scope, String grantType) {
        this.clientID = clientID;
        this.secret = secret;
        this.scope = scope;
        this.grantType = grantType;
    }

    @Override
    public String getClientId() {
        return clientID;
    }

    @Override
    public String getClientSecret() {
        return secret;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Arrays.asList(() -> scope);
    }

    @Override
    public Set<String> getScope() {
        return new HashSet<>(Arrays.asList(scope));
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return new HashSet<>(Arrays.asList(grantType));
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return new HashSet<>(Arrays.asList(redirecURI));
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getResourceIds() {
        return new HashSet<>(Arrays.asList());
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return tokenValidationSeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValidationSeconds;
    }

    @Override
    public boolean isAutoApprove(String string) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return new HashMap<>();
    }

}
