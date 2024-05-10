package com.xxxs.test.ioc;

import com.xxxs.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class InitAndDestroyMethodTest {
    @Test
    public void testInitAndDestroyMethod(){
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:init-and-destroy-method.xml");

        applicationContext.registerShutdownHook();
    }
}
