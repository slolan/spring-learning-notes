# Bean 实例化过程



Spring Framework 中的 Bean 创建是一个复杂且详细的过程，主要涉及 Bean 的定义、解析、实例化、属性填充、初始化以及可能的代理。



这个过程主要在 `BeanFactory` 中进行，具体而言，通常在 `AbstractApplicationContext` 的实现类中的 `refresh()` 方法中开始，最终通过 `DefaultListableBeanFactory` 完成。



## 读取并解析配置信息

Spring 允许通过 XML 文件、Java 注解或 Java 代码直接定义 Bean。

这些配置信息会被加载并解析成 `BeanDefinition` 对象，每一个 `BeanDefinition` 包含了：

- 类的元数据：如 Bean 类的全限定名。
- 构造函数参数：用于创建 Bean 实例的构造函数的参数。
- 属性值：需要注入到 Bean 中的属性。
- 作用域等等。



## BeanDefinition 的注册

每个 Bean 的定义信息（即 `BeanDefinition`）会被注册到 `BeanFactory` 中，通常是 `DefaultListableBeanFactory` 对象的一个 hashmap 中。

方法 `registryBeanDefinition` 定义在 `BeanDefinitionRegistry` 接口，由 `DefaultListableBeanFactory` 实现。

```java
@Override
public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
    beanDefinitionMap.put(beanName, beanDefinition);
}
```



## Bean 的实例化

Spring 在需要时才会实例化 Bean（**懒加载**），除非配置指定需要预实例化（如单例）。

这里实例化在 `AbstractAutowireCapableBeanFactory` 的 `createBean()`，它直接调用 `doCreateBean` 方法来创建 Bean 实例。

这种设计模式可以在 `createBean` 方法中添加更多的前置或后置处理逻辑。

```java
@Override
protected Object createBean(String beanName, BeanDefinition beanDefinition){
    return doCreateBean(beanName, beanDefinition);
}

protected Object doCreateBean(String beanName, BeanDefinition beanDefinition){
    Class beanClass = beanDefinition.getBeanClass();
    Object bean = null;
    
    try {
        bean = beanClass.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
        throw new BeansException("Instantiation of bean is failed.", e);
    }
    
    addSingleton(beanName, bean);
    return bean;
}
```

创建的 Bean 实例会被添加到单例池中，确保每次请求相同名称的 Bean 时都返回同一个实例。



## BeanDefinitionAndBeanDefinitionRegisterTest

```java
public class BeanDefinitionAndBeanDefinitionRegisterTest {
    @Test
    public void beanDefinitionTest() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
        beanFactory.registryBeanDefinition("HelloService", beanDefinition);

        HelloService helloService = (HelloService) beanFactory.getBean("HelloService");
        helloService.sayHello();
    }
}
```



参考：https://github.com/DerekYRC/mini-spring