package com.xxxs.beans.factory.support;

import com.xxxs.beans.BeansException;
import com.xxxs.beans.factory.config.BeanDefinition;

public class SimpleInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
//        简单的 bean 实例化策略，根据 bean 的无参构造实例化对象
        Class beanClass = beanDefinition.getBeanClass();
        try {
            return beanClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new BeansException("Failed to instantiate '" + beanClass.getName() + "'.", e);
        }
    }
}
