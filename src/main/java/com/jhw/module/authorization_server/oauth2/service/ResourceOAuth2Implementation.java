package com.jhw.module.authorization_server.oauth2.service;

import com.root101.clean.core.domain.services.ResourceHandler;
import com.root101.clean.core.domain.services.ResourceBundleUtils;
import com.root101.clean.core.domain.services.ResourceService;
import com.root101.clean.core.domain.services.DefaultResourceBundleService;
import java.net.MalformedURLException;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class ResourceOAuth2Implementation implements ResourceService {

    public static final String RESOURCE_URL = "module_seguridad_server";

    private final DefaultResourceBundleService resourceService;

    public static ResourceOAuth2Implementation init() {
        try {
            ResourceOAuth2Implementation res = new ResourceOAuth2Implementation();
            ResourceHandler.registerResourceService(res);
            return res;
        } catch (Exception e) {
        }
        return null;
    }

    private ResourceOAuth2Implementation() throws MalformedURLException {
        resourceService = new DefaultResourceBundleService(
                ResourceBundleUtils.fromInternalFile(RESOURCE_URL,
                        ResourceBundleUtils.SPANISH));
    }

    @Override
    public String getString(String string) {
        return resourceService.getString(string);
    }

    @Override
    public Object getObject(String string) {
        return resourceService.getObject(string);
    }

    @Override
    public boolean contain(String string) {
        return resourceService.contain(string);
    }
}
