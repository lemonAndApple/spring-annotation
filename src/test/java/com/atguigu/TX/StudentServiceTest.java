package com.atguigu.TX;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    @Test
    public void testTxInsert(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TXConfig.class);
        StudentService bean = context.getBean(StudentService.class);
        bean.insertStudent();
    }

}