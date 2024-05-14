package com.xxxs.test.ioc;

import com.xxxs.context.support.ClassPathXmlApplicationContext;
import com.xxxs.test.ioc.bean.Car;
import org.junit.Test;

public class PrototypeBeanTest {
    @Test
    public void testPrototypeBean(){
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:prototype-bean.xml");

        Car car1 = (Car) applicationContext.getBean("car");
        Car car2 = (Car) applicationContext.getBean("car");

        System.out.println(car1.equals(car2));
        System.out.println(car1);
        System.out.println(car2);
    }
}
