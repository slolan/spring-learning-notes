package com.xxxs.test.aop;

import com.xxxs.aop.AdvisedSupport;
import com.xxxs.aop.ClassFilter;
import com.xxxs.aop.MethodMatcher;
import com.xxxs.aop.TargetSource;
import com.xxxs.aop.aspectj.AspectJExpressionPointcut;
import com.xxxs.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.xxxs.aop.framework.CglibAopProxy;
import com.xxxs.aop.framework.JdkDynamicAopProxy;
import com.xxxs.aop.framework.ProxyFactory;
import com.xxxs.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import com.xxxs.test.common.WorldServiceBeforeAdvice;
import com.xxxs.test.common.WorldServiceInterceptor;
import com.xxxs.test.service.WorldService;
import com.xxxs.test.service.WorldServiceImpl;
import org.aopalliance.intercept.MethodInterceptor;
import org.assertj.core.api.FloatAssert;
import org.junit.Before;
import org.junit.Test;

public class DynamicProxyTest {

    private AdvisedSupport advisedSupport;

    @Before
    public void setup() {
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
    public void testDynamicProxy() {
        WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testCglibDynamicProxy() throws Exception {
        WorldService proxy = (WorldService) new CglibAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testProxyFactory() {
        advisedSupport.setProxyTargetClass(false);
        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();

        advisedSupport.setProxyTargetClass(true);
        proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testBeforeAdvice() throws Exception {
        // 设置 before advice
        WorldServiceBeforeAdvice beforeAdvice = new WorldServiceBeforeAdvice();
        MethodBeforeAdviceInterceptor methodInterceptor = new MethodBeforeAdviceInterceptor(beforeAdvice);
        advisedSupport.setMethodInterceptor(methodInterceptor);

        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testAdvisor() throws Exception {
        WorldService worldService = new WorldServiceImpl();

        // Advisor 是 Pointcut 和 Advice 的组合
        String expression = "execution(* com.xxxs.test.service.WorldService.explode(..))";
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(expression);
        MethodBeforeAdviceInterceptor methodInterceptor = new MethodBeforeAdviceInterceptor(new WorldServiceBeforeAdvice());
        advisor.setAdvice(methodInterceptor);

        ClassFilter classFilter = advisor.getPointcut().getClassFilter();
        if (classFilter.matches(worldService.getClass())) {
            AdvisedSupport advised = new AdvisedSupport();

            TargetSource targetSource = new TargetSource(worldService);
            advised.setTargetSource(targetSource);
            advised.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advised.setMethodMatcher(advisor.getPointcut().getMethodMatcher());

            WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
            proxy.explode();
        }
    }
}
