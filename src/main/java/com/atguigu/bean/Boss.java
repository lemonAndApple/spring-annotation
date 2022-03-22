package com.atguigu.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//默认加载IOC容器中的组件，容器启动时会调用无参构造器创建对象，再进行初始化赋值等操作
@Component
public class Boss {
    private Car car;

    //，标注在构造器上，构造器要用的组件都是从容器中获取
    @Autowired
    public Boss(Car car) {
        this.car=car;
        System.out.println("Boss有参构造..........");
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }

    public Car getCar() {
        return car;
    }


    //标注在方法上时，spring容器创建当前对象，就会调用方法，完成赋值
    //方法使用的参数，自定义类型的值就会从IOC容器中获取
    // @Autowired
    public void setCar(Car car) {
        this.car = car;
    }
}
