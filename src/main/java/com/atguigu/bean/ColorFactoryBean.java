package com.atguigu.bean;

import com.atguigu.Import.Color;
import org.springframework.beans.factory.FactoryBean;

//创建一个spring定义的工厂bean
public class ColorFactoryBean implements FactoryBean<Color> {

    //返回一个对象，这个对象会添加到容器中
    @Override
    public Color getObject() throws Exception {
        return new Color();
    }

    //返回对象的类型
    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    //是否单例？
    @Override
    public boolean isSingleton() {
    return true;
    }
}
