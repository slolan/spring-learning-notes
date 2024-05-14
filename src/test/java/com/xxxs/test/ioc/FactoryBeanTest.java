package com.xxxs.test.ioc;

import com.xxxs.context.support.ClassPathXmlApplicationContext;
import com.xxxs.test.ioc.bean.Car;
import com.xxxs.test.ioc.common.CarFactoryBean;
import org.junit.Test;

public class FactoryBeanTest {
    @Test
    public void testFactoryBean(){
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:factory-bean.xml");

        Car car = applicationContext.getBean("car", Car.class);
        System.out.println(car);
    }
}
