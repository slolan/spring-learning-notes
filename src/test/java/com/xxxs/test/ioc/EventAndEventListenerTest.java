package com.xxxs.test.ioc;

import com.xxxs.context.support.ClassPathXmlApplicationContext;
import com.xxxs.test.common.event.CustomEvent;
import org.junit.Test;

public class EventAndEventListenerTest {
    @Test
    public void testEventAndEventListener(){
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:event-and-event-listener.xml");

        applicationContext.publishEvent(new CustomEvent(applicationContext));

        applicationContext.registerShutdownHook();
    }
}
