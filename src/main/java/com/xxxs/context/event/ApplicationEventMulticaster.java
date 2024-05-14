package com.xxxs.context.event;

import com.xxxs.context.ApplicationEvent;
import com.xxxs.context.ApplicationListener;

/* 定义了用于管理和广播应用事件的基本操作 */
public interface ApplicationEventMulticaster {

    /* 向事件多播器添加一个事件监听器 */
    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    /* 用于将事件广播给所有注册的监听器 */
    void multicastEvent(ApplicationEvent event);

}
