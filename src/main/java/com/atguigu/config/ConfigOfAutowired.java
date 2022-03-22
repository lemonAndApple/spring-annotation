package com.atguigu.config;

import com.atguigu.Dao.BookDao;
import com.atguigu.Import.Color;
import com.atguigu.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 自动装配：
 *       spring利用依赖准入（DI），完成对IOC中各个组件的依赖关系赋值
 *
 *  1)@Autowired:自动注入
 *        1）默认按照类型优先去找容器中对应的组件
 *        2）如果找到多个相同类型组件，再将属性名作为组件的id去容器中查找
 *        3）使用@Qualifier，指定需要装配的组件的id，而不是使用属性名
 *        4)自动装配默认一定要将属性值赋好，没有就会报错
 *        可以使用Autowired(required = false)解决没有找到自动装配的问题
 *        5)@Primary:让spring自动装配的时候，默认使用首选的bean
 *          也可以使用@Qualifier指定需要装配的bean的名字（这个优先级更高）
 *
 * 2)spring还支持使用@Resoure(JSR250规范)和@Inject(JSR330规范的注解)[java规范的注解]
 *                @Resoure： 可以和@Autowired一样实现自动装配，但默认是按照组件名进行装配的
 *                没有支持@primary的功能（即用这个注解不能影响@Resource注解）,
 *                也没有Autowired(required = false)功能
 *                @Inject: 使用之前需要导入叫做inject名字的依赖,和Autowired功能一样，但没有require=false功能
 *
 * 3)@Autowired：可以在标注在构造器，方法，属性中：不管怎么放，它都是从容器中获取组件的值
 *               如果组件只有一个有参构造器，这个有参构造器上面的@Autowired可以省略，参数位置的组件还是可以自动从容器中
 *               获取
 * 4)自定义组件想要使用spring底层的一些一些组件，如ApplicationContext,BeanFactory,xxxx);
 *     自定义组件使用xxxAware即可：在创建对象时，会调用接口规定的方法注入相关组件：参照Aware接口设计(具体的例子写到了Red类中)
 *     把spring底层的一些组件注入到自定义的bean中
 */
@Configuration
@ComponentScan({"com.atguigu.service","com.atguigu.controller","com.atguigu.Dao","com.atguigu.bean",
"com.atguigu.Import"})
public class ConfigOfAutowired {

    @Primary
    @Bean("bookDao2")
    public BookDao bookDao(){
        BookDao bookDao=new BookDao();
        bookDao.setLabel("2");
        return  bookDao;
    }

    @Bean
    public Color color(Car car){
        Color color=new Color();
        color.setCar(car);
        return color;
    }

}
