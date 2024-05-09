package com.xxxs.test.ioc.common;

import com.xxxs.beans.BeansException;
import com.xxxs.beans.factory.config.BeanPostProcessor;
import com.xxxs.test.ioc.bean.Person;

public class CustomerBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomerBeanPostProcessor#postProcessBeforeInitialization");
        if ("person".equals(beanName))
            ((Person) bean).setAge(99);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomerBeanPostProcessor#postProcessAfterInitialization");
        if ("person".equals(beanName))
            ((Person) bean).setName("hhh");
        return null;
    }
}
