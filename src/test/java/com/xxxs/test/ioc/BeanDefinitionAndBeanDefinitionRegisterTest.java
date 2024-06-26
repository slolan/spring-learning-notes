package com.xxxs.test.ioc;

import com.xxxs.beans.factory.config.BeanDefinition;
import com.xxxs.beans.factory.support.DefaultListableBeanFactory;
import com.xxxs.test.service.HelloService;
import org.junit.Test;

public class BeanDefinitionAndBeanDefinitionRegisterTest {
    @Test
    public void beanDefinitionTest() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
        beanFactory.registerBeanDefinition("HelloService", beanDefinition);

        HelloService helloService = (HelloService) beanFactory.getBean("HelloService");
        helloService.sayHello();
    }
}
