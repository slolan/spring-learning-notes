package com.xxxs.test.aop;

import com.xxxs.aop.aspectj.AspectJExpressionPointcut;
import com.xxxs.test.service.HelloService;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.lang.reflect.Method;

public class PointcutExpressionTest {
    @Test
    public void testPointcutExpression() throws Exception {
        AspectJExpressionPointcut pointcut =
                new AspectJExpressionPointcut("execution(* com.xxxs.test.service.HelloService.*(..))");

        Class<HelloService> clazz = HelloService.class;
        Method sayHello = clazz.getDeclaredMethod("sayHello");

        Assertions.assertThat(pointcut.matches(clazz)).isTrue();
        Assertions.assertThat(pointcut.matches(sayHello, clazz)).isTrue();
    }
}
