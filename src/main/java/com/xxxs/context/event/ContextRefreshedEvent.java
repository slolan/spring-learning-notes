package com.xxxs.context.event;

import com.xxxs.context.ApplicationContext;

public class ContextRefreshedEvent extends ApplicationContextEvent{

    public ContextRefreshedEvent(ApplicationContext source) {
        super(source);
    }
}
