## Bean 作用域

Bean 作用域（Scope）定义了**在容器中创建的 Bean 实例的生命周期以及在应用程序中的可见性**。

Spring 提供了几种不同的作用域：

- **Singleton**：默认作用域，Spring 容器中只会创建一个单一的 bean 实例，每次请求都返回相同的对象。
- **Prototype**：每次请求都会创建一个新的 bean 实例，即每次调用 `getBean()` 时，都会创建一个新的对象。
- **Request**：每次 HTTP 请求都会创建一个新的 bean，该 bean 仅在当前 HTTP 请求内有效。
- **Session**：每次 HTTP 会话都会创建一个新的 bean，该 bean 仅在当前 HTTP 会话内有效。
- **GlobalSession**：在 Portlet 环境中使用，作用域与 Portlet 会话相同。
- **Application**：在 ServletContext 生命周期内，只创建一个 bean 实例，类似于 singleton，但作用域限定在 ServletContext 级别。



**在 prototype 作用域下的 Bean，Spring 不会负责该 Bean 的销毁周期中回调的方法**。

> - 原因：
>   - 当使用 prototype 作用域的时候，每次请求都会创建一个新的 Bean 实例。
>   - 所以 Spring 容器在创建并交付给客户端之后，不会保留对这些对象的任何引用。这就意味着 Spring 不知道已经创建了多少实例，也无法跟踪它们的状态。
>   - 如果 Spring 容器负责每一个 prototype bean 的完整生命周期，包括销毁，它必须追踪每一个被创建出来的实例。这将大大增加容器的复杂性和内存使用。
>
> 如果该 Bean 拥有一些重要的资源，想在该 Bean 对象销毁时释放这些资源，那么需要自定义 `BeanPostProcessor`（Bean 的后置处理器），它持有我们需要清理的 Bean 的引用。



## Bean 的生命周期



![](assets/prototype-bean.png)