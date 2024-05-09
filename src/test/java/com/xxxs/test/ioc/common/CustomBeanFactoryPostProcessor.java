package com.xxxs.test.ioc.common;

import com.xxxs.beans.BeansException;
import com.xxxs.beans.PropertyValue;
import com.xxxs.beans.PropertyValues;
import com.xxxs.beans.factory.ConfigurableListableBeanFactory;
import com.xxxs.beans.factory.config.BeanDefinition;
import com.xxxs.beans.factory.config.BeanFactoryPostProcessor;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("CustomBeanFactoryPostProcessor#postProcessBeanFactory");
        BeanDefinition carBeanDefinition = beanFactory.getBeanDefinition("car");
        PropertyValues propertyValues = carBeanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("brand", "xiaomi"));
    }
}
