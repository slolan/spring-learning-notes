package com.xxxs.test.aop;

import com.xxxs.aop.AdvisedSupport;
import com.xxxs.aop.MethodMatcher;
import com.xxxs.aop.TargetSource;
import com.xxxs.aop.aspectj.AspectJExpressionPointcut;
import com.xxxs.aop.framework.CglibAopProxy;
import com.xxxs.aop.framework.JdkDynamicAopProxy;
import com.xxxs.aop.framework.ProxyFactory;
import com.xxxs.test.common.WorldServiceInterceptor;
import com.xxxs.test.service.WorldService;
import com.xxxs.test.service.WorldServiceImpl;
import org.assertj.core.api.FloatAssert;
import org.junit.Before;
import org.junit.Test;

public class DynamicProxyTest {

    private AdvisedSupport advisedSupport;

    @Before
    public void setup(){
        WorldService worldService = new WorldServiceImpl();

        advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(worldService);
        WorldServiceInterceptor methodInterceptor = new WorldServiceInterceptor();
        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.xxxs.test.service.WorldService.explode(..))").getMethodMatcher();

        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(methodInterceptor);
        advisedSupport.setMethodMatcher(methodMatcher);
    }

    @Test
    public void testDynamicProxy(){
        WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testCglibDynamicProxy() throws Exception{
        WorldService proxy = (WorldService) new CglibAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testProxyFactory(){
        advisedSupport.setProxyTargetClass(false);
        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();

        advisedSupport.setProxyTargetClass(true);
        proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();
    }
}
