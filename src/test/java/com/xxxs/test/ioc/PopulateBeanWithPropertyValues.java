package com.xxxs.test.ioc;

import com.xxxs.beans.PropertyValue;
import com.xxxs.beans.PropertyValues;
import com.xxxs.beans.factory.config.BeanDefinition;
import com.xxxs.beans.factory.support.DefaultListableBeanFactory;
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

        factory.registryBeanDefinition("person", beanDefinition);
        Person person = (Person) factory.getBean("person");

        System.out.println(person);
        Assertions.assertThat(person.getName()).isEqualTo("xxxs");
        Assertions.assertThat(person.getAge()).isEqualTo(17);
    }
}
