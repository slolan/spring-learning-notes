package com.xxxs.beans.factory.config;

import com.xxxs.beans.BeansException;

public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
