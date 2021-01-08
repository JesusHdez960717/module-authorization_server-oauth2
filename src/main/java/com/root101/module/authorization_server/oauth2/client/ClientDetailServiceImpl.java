/*
 * Copyright 2021 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Or read it directly from LICENCE.txt file at the root of this project.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
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
