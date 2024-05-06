`Resource` 可以为不同类型的资源提供一个统一的接口，比如文件、类路径资源、URL资源等。



## Resource 接口的主要实现类

Spring 提供了多种 `Resource` 实现，以支持不同类型的资源访问：

- **ClassPathResource**：用于访问类路径（classpath）上的资源。例如，加载配置文件或者 Spring Bean 定义文件。

- **FileSystemResource**：用于访问文件系统上的资源。例如，读取存储在外部文件系统中的配置文件。

- **UrlResource**：用于访问基于 URL 的资源，例如文件、HTTP 目标、FTP 文件等。



## Test 遇到的问题

测试代码如下：

```java
public class ResourceAndResourceLoaderTest {
    @Test
    public void testResourceAndResourceLoader() throws Exception {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

        // 加载文件系统资源
        Resource resource = resourceLoader.getResource("src/test/resources/hello.txt");
        InputStream inputStream = resource.getInputStream();
        String str = IoUtil.readUtf8(inputStream);
        System.out.println(str);

        // 加载 URL 资源
        resource = resourceLoader.getResource("https://www.baidu.com");
        inputStream = resource.getInputStream();
        String string = IoUtil.readUtf8(inputStream);
        System.out.println(string);

        // 加载类路径下的资源
        resource = resourceLoader.getResource("classpath:hello.txt");
        String c = IoUtil.readUtf8(resource.getInputStream());
        System.out.println(c);
    }
}
```

测试前两个都没问题，最后一个加载类路径下的资源一直遇到 `InputStream` 为 `null` 的问题。

一开始把 `hello.txt` 放在了 `src/test/resources/hello.txt` 这个目录下，一直在报错。

最后看了一下 classpath 的具体路径：

```java
URL test1 = getClass().getResource("/");
URL test2 = getClass().getClassLoader().getResource("./");
URL test3 = getClass().getClassLoader().getResource("");
System.out.println(test1.getPath());
System.out.println(test2.getPath());
System.out.println(test3.getPath());
```

输出是：

```java
/D:/（自己电脑上的路径）/mini-spring/out/test/mini-spring/
/D:/（自己电脑上的路径）/mini-spring/out/test/mini-spring/
/D:/（自己电脑上的路径）/mini-spring/out/test/mini-spring/
```

根据这个路径调整了一下，把 hello.txt 放进去了，就没问题了。