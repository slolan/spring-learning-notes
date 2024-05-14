package com.xxxs.test.ioc.common.event;

import com.xxxs.context.ApplicationListener;
import com.xxxs.context.event.ContextRefreshedEvent;

public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(this.getClass().getName());
    }
}
