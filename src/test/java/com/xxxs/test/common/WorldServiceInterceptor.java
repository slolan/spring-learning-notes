package com.xxxs.test.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class WorldServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("before boom ...");
        Object result = invocation.proceed();
        System.out.println("after boom ...");
        return result;
    }
}
