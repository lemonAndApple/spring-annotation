package com.atguigu.TX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(){
        String sql="insert into students values(?,?,?) ";
        jdbcTemplate.update(sql,"桂月星",15,34);
    };
}
