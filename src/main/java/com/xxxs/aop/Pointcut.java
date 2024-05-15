package com.xxxs.aop;

/* 切点抽象 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
