package com.xxxs.beans.factory.config;

import com.xxxs.beans.BeansException;

/* 修改实例化后的 bean 的修改扩展点 */
public interface BeanPostProcessor {

    /* bean 初始化之前执行 */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /* bean 初始化之后执行 */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
