package com.atguigu.config;

import com.atguigu.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//使用@PropertySource读取外部配置文件中的k/v，保存到运行的环境变量中
@PropertySource(value = {"classpath:/person.properties"})
public class MainConfigOfPropertyValues {
    @Bean
    public Person person(){
        return new Person();
    }
}
