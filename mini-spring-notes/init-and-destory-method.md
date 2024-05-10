## bean 的初始化和销毁方法



在 spring 中，定义 bean 的初始化和销毁方法有三种方法：

- 在 xml 文件中制定 init-method 和 destroy-method
- 继承自 InitializingBean 和 DisposableBean
- 在方法上加注解 PostConstruct 和 PreDestroy （通过 BeanPostProcessor 实现）



---



1. 在 `BeanDefinition` 中增加属性 initMethodName 和 destroyMethodName。
2. 初始化方法在 AbstractAutowireCapableBeanFactory#invokeInitMethods 执行。
3. DefaultSingletonBeanRegistry 中增加属性 disposableBeans 保存拥有销毁方法的 bean，拥有销毁方法的 bean 在AbstractAutowireCapableBeanFactory#registerDisposableBeanIfNecessary 中注册到 disposableBeans 中。
4. 为了确保销毁方法在虚拟机关闭之前执行，向虚拟机中注册一个钩子方法 AbstractApplicationContext#registerShutdownHook（非web应用需要手动调用该方法）。当然也可以手动调用ApplicationContext#close 方法关闭容器。



Test 结果：

```java
I was born in the method named afterPropertiesSet
I was born in the method named customInitMethod
I died in the method named destroy
I was born in the method named customDestroyMethod
```



到此为止，bean的生命周期如下：

![](assets/init-and-destroy-method.png)