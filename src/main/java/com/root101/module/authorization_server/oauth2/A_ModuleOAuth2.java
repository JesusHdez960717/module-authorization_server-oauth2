/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.root101.module.authorization_server.oauth2;

import org.springframework.stereotype.Component;
import com.root101.module.authorization_server.oauth2.service.ResourceOAuth2Implementation;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
@Component
public class A_ModuleOAuth2 {

    public static final String BASE_PACKAGE = "com.jhw.module.authorization_server.oauth2";

    static {
        ResourceOAuth2Implementation.init();
    }
}
