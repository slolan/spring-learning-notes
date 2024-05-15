package com.xxxs.test.ioc;

import com.xxxs.context.support.ClassPathXmlApplicationContext;
import com.xxxs.test.service.HelloService;
import org.junit.Test;

public class AwareInterfaceTest {

    @Test
    public void testAwareInterface(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        HelloService helloService = applicationContext.getBean("helloService", HelloService.class);

        System.out.println(helloService.toString());
        System.out.println(helloService.getApplicationContext());
        System.out.println(helloService.getBeanFactory());
    }
}
