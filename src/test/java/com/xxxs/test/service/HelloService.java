package com.xxxs.test.service;

import com.xxxs.beans.BeansException;
import com.xxxs.beans.factory.BeanFactory;
import com.xxxs.beans.factory.BeanFactoryAware;
import com.xxxs.context.ApplicationContext;
import com.xxxs.context.ApplicationContextAware;

public class HelloService implements BeanFactoryAware, ApplicationContextAware {

    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;

    public void sayHello() {
        System.out.println("hello");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
