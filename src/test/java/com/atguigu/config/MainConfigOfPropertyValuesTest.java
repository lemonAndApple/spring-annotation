package com.atguigu.config;

import com.atguigu.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import static org.junit.jupiter.api.Assertions.*;

class MainConfigOfPropertyValuesTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);

    public void printBean(){
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String name:beanDefinitionNames){
            System.out.println(name);}
    }
    @Test
    public void testPerson(){
        printBean();
        System.out.println("=====================");
        Person person = (Person)context.getBean("person");
        System.out.println(person);
        //配置文件默认加载到环境变量中，所以这里使用加载环境从环境中获取person.nName也是可以的
        ConfigurableEnvironment environment = context.getEnvironment();
        String property = environment.getProperty("person.nName");
        System.out.println(property);
        context.close();
    }
}