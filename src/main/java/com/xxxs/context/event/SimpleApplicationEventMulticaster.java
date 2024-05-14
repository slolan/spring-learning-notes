package com.xxxs.context.event;

import com.xxxs.beans.BeansException;
import com.xxxs.beans.factory.BeanFactory;
import com.xxxs.context.ApplicationEvent;
import com.xxxs.context.ApplicationListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener<ApplicationEvent> applicationListener : applicationListeners) {
            if (supportsEvent(applicationListener, event)) {
                applicationListener.onApplicationEvent(event);
            }
        }
    }

    /* 检查给定的监听器是否支持处理指定的事件 */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        // 获取监听器实现的第一个泛型接口类型
        Type type = applicationListener.getClass().getGenericInterfaces()[0];
        // 提取该泛型类型的实际类型参数，即事件的类型
        Type actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            // 使用反射机制将事件类型的类名转换为 Class 对象
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }
        // 检查事件类型是否与当前事件的类兼容（即是否可以赋值）
        return eventClassName.isAssignableFrom(event.getClass());
    }

}
