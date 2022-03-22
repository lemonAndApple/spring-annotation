package com.atguigu.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 通过InitializingBean, DisposableBean实现初始化和销毁方法
 */
public class Cat implements InitializingBean, DisposableBean {
    public Cat() {
        System.out.println("cat constructor........");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("cat destroy...........");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("cat afterPropertiesSet.................");
    }
}
