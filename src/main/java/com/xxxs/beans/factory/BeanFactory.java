package com.xxxs.beans.factory;

import com.xxxs.beans.BeansException;

public interface BeanFactory {
    Object getBean(String name) throws BeansException;
}
