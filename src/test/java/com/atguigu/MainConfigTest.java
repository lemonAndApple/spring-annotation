package com.atguigu;

import com.atguigu.config.MainConfig;
import com.atguigu.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

class MainConfigTest {

    AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(MainConfig.class);
    @Test
    public void test(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanNamesForType = context.getBeanNamesForType(Person.class);
        for (String name:beanNamesForType){
            System.out.println(name);
        }
    }

    @Test
    public void test1(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] names = context.getBeanDefinitionNames();
        for (String name:names) System.out.println(name);
    }
    @Test
    public void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanNamesForType = context.getBeanNamesForType(Person.class);
        for (String name:beanNamesForType) System.out.println(name);
        Map<String, Person> beansOfType = context.getBeansOfType(Person.class);
//        System.out.println(beansOfType);
        ConfigurableEnvironment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println(property);
    }

    private void printBeans(AnnotationConfigApplicationContext context){
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String name:beanDefinitionNames) System.out.println(name);
    }
    @Test
    public void test3(){
        printBeans(context);
    }
    @Test
    public void testColorFactoryBean(){
        Object colorFactoryBean = context.getBean("colorFactoryBean");
        System.out.println(colorFactoryBean.getClass());
        //结果：class com.atguigu.Import.Color
        Object bean = context.getBean("&colorFactoryBean");
        System.out.println(bean.getClass());
        //result:class com.atguigu.bean.ColorFactoryBean
        //即前面加一个&符号就可以获得当前类的类名
    }

}