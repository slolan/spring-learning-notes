package com.xxxs.aop.aspectj;

import com.xxxs.aop.Pointcut;
import com.xxxs.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/* aspectJ 表达式的 advisor */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut;

    private Advice advice;

    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
        pointcut = new AspectJExpressionPointcut(expression);
    }
    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

}
