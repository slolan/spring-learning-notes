package com.xxxs.beans.factory;

import com.xxxs.beans.BeansException;

/* 实现该接口可以感知所属 BeanFactory */
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
