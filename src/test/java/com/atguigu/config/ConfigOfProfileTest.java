package com.atguigu.config;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

import static org.junit.jupiter.api.Assertions.*;

class ConfigOfProfileTest {
    /**
     * 1,使用命令行动态参数的方式：在虚拟机参数位置加载-Dspring.profiles.active=test(这个test为设置的环境标识)
     * 2,代码的方式激活某种环境
     */
    @Test
    public void testDatasource(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //1,创建一个applicationContext对象
        //2，设置需要激活的环境
        context.getEnvironment().setActiveProfiles("test");
        //3，注册主配置类
        context.register(ConfigOfProfile.class);
        //4,启动刷新容器
        context.refresh();
        String[] dataSource = context.getBeanNamesForType(DataSource.class);
        for (String s : dataSource) {
            System.out.println(s);
        }


    }

}