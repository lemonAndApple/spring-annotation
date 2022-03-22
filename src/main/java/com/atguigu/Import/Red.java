package com.atguigu.Import;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

@Component
public class Red implements ApplicationContextAware , BeanNameAware, EmbeddedValueResolverAware {
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("传入的IOC容器---->"+applicationContext);
        this.applicationContext=applicationContext;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("当前bean的名字"+name);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        String s = resolver.resolveStringValue("你好${os.name},你好#{12*78}");
        System.out.println("解析字符串："+s);
    }
    /**
     * result:
     * 当前bean的名字red
     * 解析字符串：你好Windows 10,你好936
     * 传入的IOC容器---->org.springframework.context.annotation.AnnotationConfigApplicationContext@c7b4de,
     * started on Sun Mar 20 16:58:05 CST 2022
     *
     * 注释：传入的IOC容器与测试类中使用的IOC容器一样
     */

    /**
     * 这些xxxAware的功能是使用xxxProcessor来处理的
     *    例如：ApplicationContextAware -->ApplicationContextAwareProcessor
     */
}
