package com.xxxs.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.xxxs.beans.BeansException;
import com.xxxs.beans.PropertyValue;
import com.xxxs.beans.factory.ConfigurableListableBeanFactory;
import com.xxxs.beans.factory.config.AutowireCapableBeanFactory;
import com.xxxs.beans.factory.config.BeanDefinition;
import com.xxxs.beans.factory.config.BeanPostProcessor;
import com.xxxs.beans.factory.config.BeanReference;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
        implements AutowireCapableBeanFactory {
    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;

        try {
            bean = createBeanInstance(beanDefinition);
            // 为 bean 填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
            // 执行 bean 的初始化方法
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean is failed.", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }

    // 实例化 bean
    protected Object createBeanInstance(BeanDefinition beanDefinition) {
        return getInstantiationStrategy().instantiate(beanDefinition);
    }

    // 为 bean 填充属性
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                // 先实例化依赖的 bean
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }

                // 通过反射设置属性
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Setting properties is failed for '" + beanName + "'.", e);
        }
    }

    /* 初试化 bean */
    protected Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        /* bean 的初始化方法 */
        invokeInitMethods(beanName, wrappedBean, beanDefinition);
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(existingBean, beanName);
            result = current != null ? current : result;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessAfterInitialization(existingBean, beanName);
            result = current != null ? current : result;
        }
        return result;
    }

    protected void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {
        System.out.println("执行 [" + beanName + "] 的初始化方法。");
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
