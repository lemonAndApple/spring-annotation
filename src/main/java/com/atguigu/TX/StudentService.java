package com.atguigu.TX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    //声明事物，如果方法中出现异常，则不执行这条SQL语句
    @Transactional
    public void insertStudent(){
        studentDao.insert();
        System.out.println("插入完成");

    }

}
