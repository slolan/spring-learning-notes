package com.xxxs.beans.factory.support;

import com.xxxs.beans.BeansException;
import com.xxxs.core.io.Resource;
import com.xxxs.core.io.ResourceLoader;

/* 读取 BeanDefinition 的接口*/
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String[] locations) throws BeansException;
}
