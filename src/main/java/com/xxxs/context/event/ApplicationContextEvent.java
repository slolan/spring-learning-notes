package com.xxxs.context.event;

import com.xxxs.context.ApplicationEvent;

public abstract class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationEvent getApplicationContext() {
        return (ApplicationEvent) getSource();
    }
}
