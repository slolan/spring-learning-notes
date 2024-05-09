package com.xxxs.context;

import com.xxxs.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext{

    /* 刷新容器 */
    void refresh() throws BeansException;
}
