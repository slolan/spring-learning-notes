package com.xxxs.beans.factory;

import com.xxxs.beans.BeansException;

/* bean 容器 */
public interface BeanFactory {

    /* 获取 bean */
    Object getBean(String name) throws BeansException;

    /* 根据名称和类型获取 bean */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
