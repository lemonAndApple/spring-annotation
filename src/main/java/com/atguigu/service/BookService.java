package com.atguigu.service;

import com.atguigu.Dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

@Service
public class BookService {

//    @Qualifier("bookDao")
//    @Autowired(required = false)
    //如果没找到@Qualifier中指定的bookDao2的值，则返回空
//    @Resource
    @Inject
    private BookDao bookDao;

    public void print(){
        System.out.println(bookDao);
    }

    @Override
    public String toString() {
        return "BookService{" +
                "bookDao=" + bookDao+
                '}';
    }
}
