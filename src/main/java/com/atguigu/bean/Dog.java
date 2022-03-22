package com.atguigu.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Dog {
    public Dog() {
        System.out.println("dog constructor --------");
    }

    //在bean创建完成并且属性赋值完成之后来进行初始化
    @PostConstruct
    public void init(){
        System.out.println("dog init----");
    }
    //在容器销毁之前通知进行销毁工作
    @PreDestroy
    public void destroy(){
        System.out.println("dog destroy .........");
    }

}
