package com.atguigu.entity;

import org.springframework.beans.factory.annotation.Value;

public class Person {
    /**
     * 使用@Value进行赋值
     * 1,基本数值
     * 2，可以写spEL,#{}
     * 3,可以写 ${}取出配置文件[properties]中的值（在运行的环境变量中的值）
     */
    @Value("张三")
    private String name;
    @Value("#{20-8}")
    private int age;
    @Value("${person.nName}")
    private String nName;
    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nName='" + nName + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int age, String nName) {
        this.name = name;
        this.age = age;
        this.nName = nName;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
