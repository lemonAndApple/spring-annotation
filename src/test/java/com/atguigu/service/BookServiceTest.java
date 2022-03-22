package com.atguigu.service;

import com.atguigu.Dao.BookDao;
import com.atguigu.Import.Color;
import com.atguigu.bean.Boss;
import com.atguigu.bean.Car;
import com.atguigu.config.ConfigOfAutowired;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    @Test
    public void test(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigOfAutowired.class);
        BookService bookService = context.getBean(BookService.class);
        System.out.println(bookService);
    }
    @Test
    public void testBoss(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigOfAutowired.class);
        Boss bean = context.getBean(Boss.class);
        System.out.println(bean);
        Car bean1 = context.getBean(Car.class);
        System.out.println(bean1);
        /**
         * result:
         * Boss{car=com.atguigu.bean.Car@16f6701}
         * com.atguigu.bean.Car@16f6701
         * 说明AutoWired注解在set方法上能够从IOC容器中获取对应的对象
         */
        Color bean2 = context.getBean(Color.class);
        System.out.println(bean2);
        /**
         * result:
         * Boss{car=com.atguigu.bean.Car@1d61f98}
         * com.atguigu.bean.Car@1d61f98
         * Color{car=com.atguigu.bean.Car@1d61f98}
         * 三个调用结果是一样的
         */
    }

}