## BeanFactoryPostProcessor

是 spring 提供的容器扩展机制，允许我们在 **bean 实例化前**修改 bean 的定义信息。

其重要的实现类有 `PropertyPlaceholderConfigurer` 和 `CustomEditorConfigurer`。

前者作用是用 properties 文件的配置值替换 xml 文件中的占位符，后者作用是类型转换。





## BeanPostProcessor

也是 spring 提供的容器扩展机制，不同于 BeanFactoryPostProcessor 的是，在 **bean 实例化后**修改或替换 bean。

是后面实现 AOP 的关键。

注意：**实例化**是创建对象并保存到容器中，**初始化**是对对象进行配置和设置属性。

