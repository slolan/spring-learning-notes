package com.xxxs.beans.factory.config;

import com.xxxs.beans.BeansException;
import com.xxxs.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {

    /* 执行 BeanPostProcessors 的 BeforeInitialization */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName)
            throws BeansException;

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
            throws BeansException;
}
