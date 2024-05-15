package com.xxxs.beans.factory.config;

import com.xxxs.beans.BeansException;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    /* 在 bean 实例化之前执行 */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

}
