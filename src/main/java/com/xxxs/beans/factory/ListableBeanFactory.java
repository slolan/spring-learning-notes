package com.xxxs.beans.factory;

import com.xxxs.beans.BeansException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory{

    /*
    * 返回指定类型的所有 bean
    * */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /*
    * 返回定义的所有 bean 的名称
    * */
    String[] getBeanDefinitionNames();
}
