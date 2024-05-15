package com.xxxs.test.aop;

import com.xxxs.aop.AdvisedSupport;
import com.xxxs.aop.MethodMatcher;
import com.xxxs.aop.TargetSource;
import com.xxxs.aop.aspectj.AspectJExpressionPointcut;
import com.xxxs.aop.framework.JdkDynamicAopProxy;
import com.xxxs.test.common.WorldServiceInterceptor;
import com.xxxs.test.service.WorldService;
import com.xxxs.test.service.WorldServiceImpl;
import org.junit.Test;

public class DynamicProxyTest {
    @Test
    public void testDynamicProxy(){
        WorldService worldService = new WorldServiceImpl();

        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(worldService);
        WorldServiceInterceptor methodInterceptor = new WorldServiceInterceptor();
        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.xxxs.test.service.WorldService.explode(..))").getMethodMatcher();

        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(methodInterceptor);
        advisedSupport.setMethodMatcher(methodMatcher);

        WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }
}
