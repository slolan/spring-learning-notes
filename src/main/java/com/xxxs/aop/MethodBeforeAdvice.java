package com.xxxs.aop;

import java.lang.reflect.Method;

public interface MethodBeforeAdvice extends BeforeAdvice{

    void before(Method method, Object[] objects, Object target) throws Throwable;

}
