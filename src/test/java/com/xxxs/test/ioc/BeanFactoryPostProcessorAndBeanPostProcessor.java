package com.xxxs.test.ioc;

import com.xxxs.beans.factory.support.DefaultListableBeanFactory;
import com.xxxs.beans.factory.xml.XmlBeanDefinitionReader;
import com.xxxs.test.ioc.bean.Car;
import com.xxxs.test.ioc.bean.Person;
import com.xxxs.test.ioc.common.CustomBeanFactoryPostProcessor;
import com.xxxs.test.ioc.common.CustomerBeanPostProcessor;
import org.junit.Test;

public class BeanFactoryPostProcessorAndBeanPostProcessor {
    @Test
    public void testBeanFactoryPostProcessor(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        // bean definition 加载完成后，bean 实例化之前
        CustomBeanFactoryPostProcessor beanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);
    }

    @Test
    public void testBeanPostProcessor(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        CustomerBeanPostProcessor customerBeanPostProcessor = new CustomerBeanPostProcessor();
        beanFactory.addBeanPostProcessor(customerBeanPostProcessor);

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
    }
}
