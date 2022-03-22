package com.atguigu.Import.MIBD;

import com.atguigu.Import.Black;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     *
     * @param importingClassMetadata 当前类的注解信息
     * @param registry  注册类  把所有需要添加到容器中的bean，调用这个方法手工注册进来
     * @param importBeanNameGenerator
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        boolean blue = registry.containsBeanDefinition("com.atguigu.Import.Blue");
        boolean yellow = registry.containsBeanDefinition("com.atguigu.Import.Yellow");
        if (blue && yellow){
            //指定bean的定义信息(bean的类型)
            RootBeanDefinition beanDefinition = new RootBeanDefinition(Black.class);
            //注册一个bean，指定bean名
            registry.registerBeanDefinition("Black",beanDefinition);
        }
    }
}
