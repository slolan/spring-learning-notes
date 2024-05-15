package com.xxxs.test.common.event;

import com.xxxs.context.event.ApplicationContextEvent;

public class CustomEvent extends ApplicationContextEvent {
    public CustomEvent(Object source) {
        super(source);
    }
}
