package com.xxxs.test.common;

import com.xxxs.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class WorldServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object target) throws Throwable {
        System.out.println("BeforeAdvice: before the earth explodes !!");
    }
}
