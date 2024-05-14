FactoryBean 是一种特殊的 bean，当向容器获取该 bean 时，容器不是返回其本身，而是返回其 FactoryBean#getObject 方法的返回值，可通过编码方式定义复杂的 bean。

实现逻辑比较简单，当容器发现 bean 为 FactoryBean 类型时，调用其 getObject 方法返回最终 bean。当 FactoryBean#isSingleton==true，将最终 bean 放进缓存中，下次从缓存中获取。