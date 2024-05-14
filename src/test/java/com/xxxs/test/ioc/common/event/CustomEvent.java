package com.xxxs.test.ioc.common.event;

import com.xxxs.context.event.ApplicationContextEvent;

public class CustomEvent extends ApplicationContextEvent {
    public CustomEvent(Object source) {
        super(source);
    }
}
