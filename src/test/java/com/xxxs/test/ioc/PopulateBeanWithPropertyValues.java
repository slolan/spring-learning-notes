package com.xxxs.test.ioc;

import com.xxxs.beans.PropertyValue;
import com.xxxs.beans.PropertyValues;
import com.xxxs.beans.factory.config.BeanDefinition;
import com.xxxs.beans.factory.config.BeanReference;
import com.xxxs.beans.factory.support.DefaultListableBeanFactory;
import com.xxxs.test.ioc.bean.Car;
import com.xxxs.test.ioc.bean.Person;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class PopulateBeanWithPropertyValues {

    @Test
    public void testPopulateBeanWithPropertyValues(){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "xxxs"));
        propertyValues.addPropertyValue(new PropertyValue("age", 17));

        BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValues);

        factory.registerBeanDefinition("person", beanDefinition);
        Person person = (Person) factory.getBean("person");

        System.out.println(person);
        Assertions.assertThat(person.getName()).isEqualTo("xxxs");
        Assertions.assertThat(person.getAge()).isEqualTo(17);
    }

    @Test
    public void testPopulateBeanWithBean(){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        // 注册 Car 实例
        PropertyValues carPropertyValues = new PropertyValues();
        carPropertyValues.addPropertyValue(new PropertyValue("brand", "hongqi"));
        BeanDefinition carBeanDefinition = new BeanDefinition(Car.class, carPropertyValues);
        factory.registerBeanDefinition("car", carBeanDefinition);

        // 注册 Person 实例
        PropertyValues personPropertyValues = new PropertyValues();
        personPropertyValues.addPropertyValue(new PropertyValue("name", "xxxs"));
        personPropertyValues.addPropertyValue(new PropertyValue("age", 17));

        // person 实例依赖 car 实例
        personPropertyValues.addPropertyValue(new PropertyValue("car", new BeanReference("car")));

        BeanDefinition personBeanDefinition = new BeanDefinition(Person.class, personPropertyValues);
        factory.registerBeanDefinition("person", personBeanDefinition);

        Person person = (Person) factory.getBean("person");
        System.out.println(person.toString());

        Car car = person.getCar();
        System.out.println(car.toString());
    }
}
