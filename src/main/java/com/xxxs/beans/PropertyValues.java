package com.xxxs.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv) {
        for (int i = 0; i < propertyValueList.size(); i++) {
            PropertyValue curPv = propertyValueList.get(i);
            if (curPv.getName().equals(pv.getName())) {
                // 覆盖原有的属性值
                propertyValueList.set(i, pv);
                return;
            }
        }
        propertyValueList.add(pv);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (int i = 0; i < propertyValueList.size(); i++) {
            PropertyValue pv = propertyValueList.get(i);
            if (pv.getName().equals(propertyName))
                return pv;
        }
        return null;
    }
}
