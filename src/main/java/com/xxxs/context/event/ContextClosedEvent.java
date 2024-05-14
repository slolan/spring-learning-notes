package com.xxxs.context.event;

import com.xxxs.context.ApplicationContext;

public class ContextClosedEvent extends ApplicationContextEvent{
    public ContextClosedEvent(ApplicationContext source) {
        super(source);
    }
}
