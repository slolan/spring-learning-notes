package com.xxxs.beans.factory.config;

// BeanDefinition 实例保存 bean 的信息，包括 class 类型、方法构造参数、是否为单例
// 可以看源码：https://github.com/spring-projects/spring-framework/blob/main/spring-beans/src/main/java/org/springframework/beans/factory/config/BeanDefinition.java
// 这里简化为只包含 class 类型
public class BeanDefinition {
    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
