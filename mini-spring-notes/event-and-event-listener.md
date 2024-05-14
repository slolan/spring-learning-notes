在 Spring 框架中，容器事件和事件监听器机制是一种允许不同组件之间进行通信的方式，而不需要这些组件之间有直接的联系。这种机制基于观察者模式，其中事件源（比如 Spring 容器）会产生事件，而事件监听器则监听这些事件并作出响应。

## 容器事件

容器事件是 Spring 框架中由容器触发的事件。这些事件通常与应用程序上下文（ApplicationContext）的生命周期有关，例如初始化完成、刷新、关闭等。

## 事件监听器

事件监听器是实现了特定接口的对象，用来接收和处理事件。在 Spring 中，可以通过实现 `ApplicationListener` 接口来创建一个监听器，该接口定义了一个方法 `onApplicationEvent()` ，用于处理接收到的事件。

## 事件发布

可以自定义事件并发布它们。自定义事件应该继承 `ApplicationEvent` 。

事件的发布通常通过 `ApplicationEventPublisher` 接口完成，它可以在需要的地方注入到组件中。

---

ApplicationContext 容器提供了完善的事件发布和事件监听功能。

ApplicationEventMulticaster 接口是注册监听器和发布事件的抽象接口。

AbstractApplicationContext 包含 ApplicationEventMulticaster 的实现类实例作为其属性，使得ApplicationContext容器具有注册监听器和发布事件的能力。

在AbstractApplicationContext#refresh方法中，会实例化ApplicationEventMulticaster、注册监听器并发布容器刷新事件 ContextRefreshedEven。

在AbstractApplicationContext#doClose方法中，发布容器关闭事件ContextClosedEvent。

