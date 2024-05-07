package com.xxxs.beans.factory.support;

import com.xxxs.beans.BeansException;
import com.xxxs.beans.factory.config.BeanDefinition;

// BeanDefinition 注册接口
public interface BeanDefinitionRegistry {

    /* 向注册表中注册 BeanDefinition */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /* 根据名称查找 BeanDefinition */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /* 根据名称判断是否包含 BeanDefinition */
    boolean containsBeanDefinition(String beanName);

    /* 返回定义的所有 Bean 的名称 */
    String[] getBeanDefinitionNames();
}
