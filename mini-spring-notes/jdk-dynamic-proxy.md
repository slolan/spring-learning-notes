AopProxy 是获取代理对象的抽象接口。

JdkDynamicAopProxy 是基于 JDK 动态代理的具体实现。

TargetSource，被代理对象的封装。

MethodInterceptor，方法拦截器，是 AOP Alliance 的"公民"，顾名思义，可以拦截方法，可在被代理执行的方法前后增加代理行为。