package com.atguigu.config;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class MainConfigOfLifeCycleTest {
@Test
    public void testCar(){
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
    System.out.println("容器创建完成");
    context.close();
    //result:
    //       car constructor........
    //       car init
    //       容器创建完成
    //       car ..........destory.....
}
@Test
    public void testCat(){
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
    System.out.println("容器创建完成");
    context.close();
    /**
     * result:
     * cat constructor........
     * cat afterPropertiesSet.................
     * 容器创建完成
     * cat destroy...........
     */
}
@Test
    public void testDog(){
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
    System.out.println("容器创建完成");
    context.close();
    /**
     * result:
     * dog constructor --------
     * dog init----
     * 容器创建完成
     * dog destroy .........
     */
}
}