package com.atguigu.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @Aspect 告诉spring当前类是一个切面类
 */
@Aspect
public class LogAspects {

    //抽取公共的切入点表达式

    /**
     * 1,本类引用
     * 2，其他类引用
     */
    @Pointcut("execution(* com.atguigu.aop.MathCalculator.*(..))")
    public void pointCut(){}
    //在目标方法之前切入，切入表达式（指定在哪个表达式切入）
    @Before("pointCut()")
    //JoinPoint参数一定要放在参数列表的第一位，否则会出现错误
    public void logStart(JoinPoint joinPoint){
        //获取方法的方法名以及参数列表
        Object[] args = joinPoint.getArgs();
        System.out.println("the method ->'"+joinPoint.getSignature().getName()+"' division running ..... " +
                "@Before: the list of parameters are {"+ Arrays.asList(args)+"}");

    }

    @After("pointCut()")
    public void logEnd(){
        System.out.println("division in the end........");
    }

    //returning 指定接收这个方法返回的对象
    @AfterReturning(value = "pointCut()",returning = "result")
    public void logReturn(Object result){
        System.out.println("division normally return point,the running result is {"+result+"}");
    }

    //throwing 指定接收这个出现的异常
    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logException(JoinPoint joinPoint,Exception exception){
        System.out.println("the method->'"+joinPoint.getSignature().getName()+"' division return exception ," +
                "the information of it is {"+exception+"}");
    }
}
