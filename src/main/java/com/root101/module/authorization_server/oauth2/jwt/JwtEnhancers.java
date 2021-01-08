/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.root101.module.authorization_server.oauth2.jwt;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.Authentication;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class JwtEnhancers {

    private static final List<Function<Authentication, Map<String, Object>>> additionalInfo = new ArrayList<>();

    public static void addAdditionalInformation(Function<Authentication, Map<String, Object>> function) {
        additionalInfo.add(function);
    }

    public static Map<String, Object> getAdditionalInformation(Authentication user) {
        Map<String, Object> map = new LinkedHashMap<>();
        for (Function<Authentication, Map<String, Object>> function : additionalInfo) {
            map.putAll(function.apply(user));
        }
        return map;
    }
}
