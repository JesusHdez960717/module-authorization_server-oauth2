/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.authorization_server.oauth2.permission;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
@Component
@Aspect
public class AspectAccessLevel {

    @Before("@annotation(AccessLevel)")
    public void test(JoinPoint joinPoint) throws Exception {
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        AccessLevel annot = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(AccessLevel.class);
        System.out.println("Value " + annot.value());
        throw new Exception("123");
    }
}
