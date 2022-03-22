package com.atguigu.TX;

public class Student {
    private String name;
    private String number;
    private String age;

    public Student() {
    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Student(String name, String number, String age) {
        this.name = name;
        this.number = number;
        this.age = age;
    }
}
