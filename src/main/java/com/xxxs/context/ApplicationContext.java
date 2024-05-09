package com.xxxs.context;

import com.xxxs.beans.factory.HierarchicalBeanFactory;
import com.xxxs.beans.factory.ListableBeanFactory;
import com.xxxs.core.io.ResourceLoader;

/* 应用上下文 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader {
}
