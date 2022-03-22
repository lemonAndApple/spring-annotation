package com.atguigu.config;

import com.atguigu.bean.Car;
import com.atguigu.bean.Cat;
import com.atguigu.bean.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * bean的生命周期：
 *          bean的创建----初始化----销毁过程
 * 容器管理bean的生命周期：
 * 我们可以自定义初始化和销毁方法：容器在bean进行到当前生命周期的时候来调用自定义的初始化和销毁方法
 *
 * 初始化：
 *       对象创建完成，并赋值好之后，调用初始化方法
 * 销毁：
 *      单实例：对象关闭的时候调用销毁方法
 *      多实例：容器不会管理这个bean，所以容器不会销毁
 *
 * 1）通过@Bean注解指定初始化和销毁方法initMethod destroyMethod
 * 2)通过让bean实现InitializingBean(定义初始化逻辑)
 *                DisposableBean(定义销毁逻辑)
 *3)可以使用JSR250:
 *           @PsotConstruct  ：在bean创建完成并且属性赋值完成之后来进行初始化
 *           @PreDestroy     :在容器销毁之前通知进行销毁工作
 * 4)beanPostProcessor:bean的后置处理器：在bean的初始化的前后进行处理的工作
 *                     postProcessBeforeInitialization:在初始化之前工作
 *                     postProcessAfterInitialization：在初始化之后工作
 */
@Configuration
@ComponentScan("com.atguigu.bean")
public class MainConfigOfLifeCycle {
    @Bean(initMethod = "init",destroyMethod = "destory")
    public Car car(){
        return new Car();
    }
    @Bean
    public Cat cat(){
        return new Cat();
    }
    @Bean
    public Dog dog(){
        return new Dog();
    }

}
