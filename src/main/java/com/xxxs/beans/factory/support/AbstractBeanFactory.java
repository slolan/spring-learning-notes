package com.xxxs.beans.factory.support;

import com.xxxs.beans.BeansException;
import com.xxxs.beans.factory.BeanFactory;
import com.xxxs.beans.factory.FactoryBean;
import com.xxxs.beans.factory.config.BeanDefinition;
import com.xxxs.beans.factory.config.BeanPostProcessor;
import com.xxxs.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    private final Map<String, Object> factoryBeanObjectCache = new HashMap<>();

    @Override
    public Object getBean(String name) throws BeansException {
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            return getObjectForBeanInstance(sharedInstance, name);
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition);
        return getObjectForBeanInstance(bean, name);
    }

    /* 如果是 factory bean 则从 FactoryBean#getObject 中创建 bean */
    protected Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        Object object = beanInstance;

        if (beanInstance instanceof FactoryBean factoryBean) {
            try {
                if (factoryBean.isSingleton()) {
                    // singleton 作用域 bean，从缓存中读取
                    object = factoryBeanObjectCache.get(beanName);
                    if (object == null) {
                        object = factoryBean.getObject();
                        factoryBeanObjectCache.put(beanName, object);
                    }
                } else {
                    // prototype 作用域 bean，新建 bean
                    object = factoryBean.getObject();
                }
            } catch (Exception e) {
                throw new BeansException("FactoryBean threw exception on object [" + beanName + "] creation, " + e);
            }
        }

        return object;
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        // 有则覆盖
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }
}
