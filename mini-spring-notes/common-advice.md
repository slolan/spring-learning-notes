**几种常用的Advice：**

- **BeforeAdvice**

- **AfterAdvice**

- **AfterReturningAdvice**

- **ThrowsAdvice**

只简单实现 BeforeAdvice。

定义 MethodBeforeAdviceInterceptor 拦截器，在执行被代理方法之前，先执行 BeforeAdvice 的方法。

