/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.authorization_server.oauth2;

import com.jhw.module.admin.seguridad.core.module.SeguridadCoreModule;
import org.springframework.stereotype.Component;
import com.jhw.module.admin.seguridad.core.usecase_def.*;
import com.jhw.module.admin.seguridad.repo.module.SeguridadRepoModule;
import com.jhw.module.authorization_server.oauth2.service.ResourceOAuth2Implementation;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
@Component
public class A_ModuleOAuth2 {

    public static final String BASE_PACKAGE = "com.jhw.module.authorization_server.oauth2";

    public final static UsuarioUseCase usuarioUC;
    public final static ClienteUseCase clienteUC;

    static {
        ResourceOAuth2Implementation.init();

        SeguridadCoreModule.init();

        usuarioUC = SeguridadCoreModule.getInstance().getImplementation(UsuarioUseCase.class);
        clienteUC = SeguridadCoreModule.getInstance().getImplementation(ClienteUseCase.class);
    }
}
