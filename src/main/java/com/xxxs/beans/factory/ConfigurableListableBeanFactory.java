package com.xxxs.beans.factory;

import com.xxxs.beans.BeansException;
import com.xxxs.beans.factory.config.AutowireCapableBeanFactory;
import com.xxxs.beans.factory.config.BeanDefinition;
import com.xxxs.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    /*
    * 根据名称找 BeanDefinition
    * */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
