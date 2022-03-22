package com.atguigu.config;

import com.atguigu.Import.Yellow;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * profile:
 *         spring为我们提供的可以根据当前环境，动态激活和切换一系列组件的功能
 *
 * 开发环境，测试环境，生产环境不同数据源的切换用到@Profile
 *
 * @Profile :指定组件在哪个环境下才能被注册到容器之中，里面的value作为环境的表示
 *          1)加了环境标识的bean，只有这个环境被激活时候才会注册到容器之中，如果value=“default”表示设置为默认环境，
 *          可以注册到组件
 *          2)这个注解写到配置类上时， 只有配置类满足响应的环境条件时，整个配置类中的所有配置才会生效
 *
 */
@Configuration
@PropertySource("classpath:/db.properties")
public class ConfigOfProfile  implements EmbeddedValueResolverAware {

    //值解析器
    private StringValueResolver valueResolver;
    //用DriverClass获取值解析器解析的db.Driver
    private String DriverClass;
    @Value("${db.user}")
    private String user;

    @Profile("test")
    @Bean
    public Yellow yellow(){
        System.out.println("yellow");
        return new Yellow();
    }

    @Profile("product")
    @Bean("proDatasource")
    public DataSource dataSourceProduct(@Value("${db.password}")String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/student");
        String s = valueResolver.resolveStringValue("${db.Driver}");
        dataSource.setDriverClass(DriverClass);
        return dataSource;
    }
    @Profile("test")
    @Bean("testDatasource")
    public DataSource dataSourceTest(@Value("${db.password}")String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        String s = valueResolver.resolveStringValue("${db.Driver}");
        dataSource.setDriverClass(DriverClass);
        return dataSource;
    }


    @Profile("develop")
    @Bean("devDataSource")
    public DataSource dataSourceDevelop(@Value("${db.password}")String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/develop");
        dataSource.setDriverClass(DriverClass);
        return dataSource;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.valueResolver=resolver;
        DriverClass=valueResolver.resolveStringValue("${db.Driver}");
    }
}
