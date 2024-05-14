package com.xxxs.context.support;

import com.xxxs.beans.BeansException;
import com.xxxs.beans.factory.ConfigurableListableBeanFactory;
import com.xxxs.beans.factory.config.BeanFactoryPostProcessor;
import com.xxxs.beans.factory.config.BeanPostProcessor;
import com.xxxs.context.ApplicationEvent;
import com.xxxs.context.ApplicationListener;
import com.xxxs.context.ConfigurableApplicationContext;
import com.xxxs.context.event.ApplicationEventMulticaster;
import com.xxxs.context.event.ContextRefreshedEvent;
import com.xxxs.context.event.SimpleApplicationEventMulticaster;
import com.xxxs.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {
        // 创建 BeanFactory 并加载 BeanDefinition
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 添加 ApplicationContextAwareProcessor，让继承 ApplicationContextAware 的 bean 能感知
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 在 bean 实例化之前执行 BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        // BeanPostProcessor 需要在其他 bean 实例化之前注册
        registerBeanPostProcessors(beanFactory);

        // 初始化事件发布者
        initApplicationEventMulticaster();

        // 注册事件监听器
        registerListeners();

        // 提前实例化单例 bean
        beanFactory.preInstantiateSingletons();

        // 发布 容器刷新完成事件
        finishRefresh();
    }

    // 创建 BeanFactory 并加载 BeanDefinition
    protected abstract void refreshBeanFactory() throws BeansException;

    // 在 bean 实例化之前执行 BeanFactoryPostProcessor
    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    // BeanPostProcessor 需要在其他 bean 实例化之前注册
    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    // 初始化事件发布者
    protected void initApplicationEventMulticaster(){
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.addSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    // 注册事件监听器
    protected void registerListeners(){
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener applicationListener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(applicationListener);
        }
    }

    // 发布容器刷新完成事件
    protected void finishRefresh(){
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    public abstract ConfigurableListableBeanFactory getBeanFactory();

    public void close() {
        doClose();
    }

    public void registerShutdownHook() {
        Thread shutdownHook = new Thread() {
            public void run() {
                doClose();
            }
        };
        /* 将线程添加为 JVM 的关闭钩子，当 JVM 关闭时，这个钩子将被调用 */
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    protected void doClose() {
        // 发布容器关闭事件
        publishEvent(new ContextRefreshedEvent(this));

        // 执行单例 bean 的销毁方法
        destroyBeans();
    }

    protected void destroyBeans() {
        getBeanFactory().destroySingletons();
    }
}
