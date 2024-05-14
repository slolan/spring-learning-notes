package com.xxxs.test.ioc.common.event;

import com.xxxs.context.ApplicationListener;
import com.xxxs.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println(this.getClass().getName());
    }
}
