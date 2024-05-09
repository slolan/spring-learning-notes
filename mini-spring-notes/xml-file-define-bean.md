## 在 xml 文件中定义 bean



有了资源加载器，就可以在 xml 格式配置文件中声明式地定义 bean 的信息：

​	资源加载器读取 xml 文件，解析出 bean 的信息，然后往容器中注册 BeanDefinition。



- `BeanDefinitionReader` 是读取 bean 定义信息的抽象接口。
- `XmlBeanDefinitionReader` 是从 xml 文件中读取的实现类。
- `BeanDefinitionReader` 需要有获取资源的能力，且读取 bean 定义信息后需要往容器中注册 BeanDefinition，因此 BeanDefinitionReader 的抽象实现类 `AbstractBeanDefinitionReader` 拥有 ResourceLoader 和 BeanDefinitionRegistry 两个属性。



由于从 xml 文件中读取的内容是 String 类型，所以属性仅支持 String 类型和引用其他 Bean。

后面会补充属性编辑器 PropertyEditor，实现类型转换。





## 容器加载相关类图



又调整了 Bean Factory 及其一系列的层次结构：

![](assets/DefaultListableBeanFactory140507.png)



- `SingletonBeanRegistry`：定义了对单例的注册及获取。
- `BeanFactory`：定义了**获取** Bean 和 Bean 的各种属性。
- `DefaultSingletonBeanRegistry`：对 SingletonBeanRegistry 各函数的实现。
- `HierarchicalBeanFactory`：继承 BeanFactory，在 Spring 框架里是实现了对 parentFactory 的支持，这里就当一个简单的接口。
- `BeanDefinitionRegistry`：定义对 BeanDefinition 的各种增删改操作，这里只简单实现了增。
- `ConfigurableBeanFactory`：人如其名，Spring 框架里是提供配置 Factory 的各种方法，这里当简单接口。
- `ListableBeanFactory`：根据各种条件获取 Bean 的配置清单，这里实现的很简单。

- `AbstractBeanFactory`：综合 DefaultSingletonBeanRegistry 和 ConfigurableBeanFactory 的功能。
- `AutowiredCapableBeanFactory`：Spring 框架里是提供创建 Bean、自动注入、初始化以及应用 Bean 的后处理器。
