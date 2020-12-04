/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.authorization_server.oauth2.client;

import com.jhw.module.admin.seguridad.core.domain.ClienteDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class ClientDetailsWrapper implements ClientDetails {

    public static final String SCOPES = "all";

    public final static ClientDetailsWrapper from(PasswordEncoder passwordEncoder, ClienteDomain client) {
        return new ClientDetailsWrapper(passwordEncoder, client);
    }

    private final ClienteDomain client;

    private final PasswordEncoder passwordEncoder;

    public ClientDetailsWrapper(PasswordEncoder passwordEncoder, ClienteDomain client) {
        this.client = client;
        this.passwordEncoder = passwordEncoder;
    }

    private Collection<String> split(String text) {
        String[] spl = text.split(",");
        Collection<String> l = new ArrayList<>();
        for (String str : spl) {
            l.add(str.trim().toLowerCase());
        }
        return l;
    }

    @Override
    public String getClientId() {
        return client.getNombreCliente();
    }

    @Override
    public String getClientSecret() {
        return passwordEncoder.encode(client.getSecret());
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Arrays.asList(() -> "");
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return new HashSet<>(split(client.getGrantTypesFk().getNombreGrantTypes()));
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return new HashSet<>(split(client.getRedirectURI()));
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
    public Set<String> getScope() {
        return new HashSet<>(Arrays.asList(SCOPES));
    }

    @Override
    public Set<String> getResourceIds() {
        return new HashSet<>();
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return client.getTokenValidationSeconds();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return client.getRefreshTokenValidationSeconds();
    }

    @Override
    public boolean isAutoApprove(String string) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return new HashMap<>();
    }

}
