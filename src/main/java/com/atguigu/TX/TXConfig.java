package com.atguigu.TX;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 声明式事物：
 *
 *  环境搭建：
 *     1)导入相关依赖
 *            数据源，数据驱动，sprng-jdbc模块
 *     2）配置数据源和jdbc-Template操作数据
 *     3)@Transactional 在相应方法上声明当前方法事物的注解
 *     4)@EnableTransactionManagement 开启基于注解的事物管理功能
 *     5)在容器中注册事物管理器
 */
@EnableTransactionManagement
@Configuration
@ComponentScan("com.atguigu.TX")
public class TXConfig {

    //数据源
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource data=new ComboPooledDataSource();
        data.setUser("root");
        data.setPassword("123456");
        data.setJdbcUrl("jdbc:mysql://localhost:3306/student");
        data.setDriverClass("com.mysql.cj.jdbc.Driver");
        return data;
    }


    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        //注册了一次组件（dataSource），剩下的多次调用都会从容器中找相应的组件
        JdbcTemplate j=new JdbcTemplate(dataSource());
        return j;
    }

    //注册事物管理器
    @Bean
    public PlatformTransactionManager platformTransactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSource());
    }
}
