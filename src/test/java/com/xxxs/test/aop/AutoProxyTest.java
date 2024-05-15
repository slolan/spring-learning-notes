package com.xxxs.test.aop;

import com.xxxs.context.support.ClassPathXmlApplicationContext;
import com.xxxs.test.service.WorldService;
import org.junit.Test;

public class AutoProxyTest {
    @Test
    public void testAutoProxy(){
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:auto-proxy.xml");

        WorldService worldService = applicationContext.getBean("worldService", WorldService.class);
        worldService.explode();
    }
}
