package com.xxxs.test.ioc;

import cn.hutool.core.io.IoUtil;
import com.xxxs.core.io.*;
import org.junit.Test;

import java.io.InputStream;

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
