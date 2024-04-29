package com.xxxs.beans.factory.support;

import com.xxxs.beans.BeansException;
import com.xxxs.beans.factory.config.BeanDefinition;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition) throws BeansException;
}
