package com.atguigu.config;

import com.atguigu.aop.MathCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class MainConfigOfAopTest {

    @Test
    public void testMainConfigOfAopTest(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfAop.class);
        MathCalculator mathCalculator = context.getBean(MathCalculator.class);
        mathCalculator.div(12,1);
        context.close();

    }

}