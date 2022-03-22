package com.atguigu.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 后置处理器：初始化前后进行处理
 */
//将后置处理器加入容器中
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization...."+beanName+"---->"+bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization...."+beanName+"---->"+bean);
        return bean;
    }
    /**
     * 遍历得到容器中所有的BeanPostProcessor方法，挨个执行 BeforeInitialization，一旦返回null，
     * 跳出for循环，不会执行后面的BeanPostProcessor.postProcessorBeforeInitialization方法
     *
     * spring底层对BeanPostprocessor的使用：
     *           bean赋值，注入其他组件，@Autowired,生命周期注解功能，@Async,xxx BeanPostprocessor都用到了它；
     */
}
