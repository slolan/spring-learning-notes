package com.xxxs.beans;

public class PropertyValue {
    /**
     * 用于保存单个 bean 属性的信息和值。
     * 这里没有选择将所有属性存储在一个以属性名称为键的映射中，
     * 可以提高灵活性，并能以优化的方式处理索引属性等。
     */
    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}