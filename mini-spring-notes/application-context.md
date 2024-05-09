## 应用上下文 ApplicationContext



是 spring 中较之于 BeanFactory 更为先进的IOC容器，ApplicationContext 除了拥有 BeanFactory 的**所有功能**外，还支持特殊类型 bean 如上一节中的 BeanFactoryPostProcessor 和 BeanPostProcessor 的自动识别、资源加载、容器事件和监听器、国际化支持、单例 bean 自动初始化等。

- BeanFactory 是spring 的基础设施，面向 spring 本身。

- ApplicationContext 面向 spring 的使用者，应用场合使用 ApplicationContext。



## ApplicationContextTest

在 xml 文件里配置 BeanFactoryPostProcessor 和 BeanPostProcessor：

```xml
...

<bean class="com.xxxs.test.ioc.common.CustomBeanFactoryPostProcessor"/>

<bean class="com.xxxs.test.ioc.common.CustomerBeanPostProcessor"/>

...
```

输出：

```java
执行 [customBeanFactoryPostProcessor] 的初始化方法。
CustomBeanFactoryPostProcessor#postProcessBeanFactory
执行 [customerBeanPostProcessor] 的初始化方法。
CustomerBeanPostProcessor#postProcessBeforeInitialization, beanName：car
执行 [car] 的初始化方法。
CustomerBeanPostProcessor#postProcessAfterInitialization, beanName：car
CustomerBeanPostProcessor#postProcessBeforeInitialization, beanName：person
执行 [person] 的初始化方法。
CustomerBeanPostProcessor#postProcessAfterInitialization, beanName：person
Person{name='hhh', age=99, car=Car{brand='xiaomi'}}
Car{brand='xiaomi'}
```

其中，输出第二行的 `CustomBeanFactoryPostProcessor#postProcessBeanFactory` 是在 `customerBeanPostProcessor` 实例化之前执行的。

在整个输出中，只执行了一次 postProcessBeanFactory 这个方法，原因见 <a href=".\mini-spring-notes\bean-factory-post-processor-and-bean-post-processor.md">容器扩展机制 BeanFactoryPostProcess 和 BeanPostProcessor</a>，前者在 bean 实例化之前执行，后者在实例化之后执行。



---



从bean的角度看，目前生命周期如下：

XML 文件  -->  BeanDefinition 加载  -->  BeanFactoryPostProcessor 修改 BeanDefinition  -->  bean 实例化  -->  BeanPostProcessor 前置处理  -->  执行 bean 的初始化方法  -->  BeanPostProcessor 后置处理  -->  使用