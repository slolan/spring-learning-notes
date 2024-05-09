package com.xxxs.beans.factory.config;

import com.xxxs.beans.BeansException;
import com.xxxs.beans.factory.ConfigurableListableBeanFactory;

/* 允许自定义修改 bean definition 的属性值 */
public interface BeanFactoryPostProcessor {

    /* 在所有 BeanDefinition 加载完成后 在 bean 实例化之前，提供修改属性值 */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
