package com.xxxs.test.ioc;

import com.xxxs.context.support.ClassPathXmlApplicationContext;
import com.xxxs.test.ioc.bean.Car;
import com.xxxs.test.ioc.bean.Person;
import org.junit.Test;

public class ApplicationContextTest {

    @Test
    public void testApplicationContext(){
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring.xml");

        Person person = (Person) applicationContext.getBean("person");
        Car car = (Car) applicationContext.getBean("car");

        System.out.println(person);
        System.out.println(car);
    }
}
