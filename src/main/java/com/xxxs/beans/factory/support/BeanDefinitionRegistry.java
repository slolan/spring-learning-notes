package com.xxxs.beans.factory.support;

import com.xxxs.beans.factory.config.BeanDefinition;

// BeanDefinition 注册接口
public interface BeanDefinitionRegistry {
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
