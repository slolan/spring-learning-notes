package com.xxxs.context;

import com.xxxs.beans.BeansException;
import com.xxxs.beans.factory.Aware;

/* 实现该接口可以感知所属 application context */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
