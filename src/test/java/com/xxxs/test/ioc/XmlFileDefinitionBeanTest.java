package com.xxxs.test.ioc;

import com.xxxs.beans.factory.support.DefaultListableBeanFactory;
import com.xxxs.beans.factory.xml.XmlBeanDefinitionReader;
import com.xxxs.test.bean.Car;
import com.xxxs.test.bean.Person;
import org.junit.Test;

public class XmlFileDefinitionBeanTest {

    @Test
    public void testXml(){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(factory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        Person person = (Person) factory.getBean("person");
        System.out.println(person);

        Car car = (Car) factory.getBean("car");
        System.out.println(car);
    }
}
