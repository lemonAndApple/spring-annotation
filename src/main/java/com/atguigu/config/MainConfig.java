package com.atguigu.config;

import com.atguigu.Import.Color;
import com.atguigu.Import.MIBD.MyImportBeanDefinitionRegistrar;
import com.atguigu.Import.Red;
import com.atguigu.Import.MyImportSelector.MyImportSelecor;
import com.atguigu.bean.ColorFactoryBean;
import com.atguigu.condition.LinuxCondition;
import com.atguigu.condition.WindowsCondition;
import com.atguigu.entity.Person;
import org.springframework.context.annotation.*;

//Condition放在类上，满足当前条件，这个类中的所有bean才会注册生效
//@Conditional(WindowsCondition.class)
@Configuration
/**
 * 给容器中注册组件：
 * 1）包扫描+组件标注注解(@Service ,@Repository ，@Controller,@Component)
 * 2)@bean[导入的第三方包里面的组件]
 * 3)@Import[快速给容器中导入一个组件]
 *       1)@import(要导入的组件)容器中就会自动注册这个组件，id默认是全类名
 *       2）ImportSelector:返回需要导入的全类名数组;
 *       3)	@ImportBeanDefinitionRegistrar：手动注册
 * 4)使用spring提供的FactoryBean(工厂bean)
 *       1)默认获取到的是工厂bean调用getObject创建的对象，要想获取工厂bean本身，则在getBean时
 *       id前面加一个&符号
 *
 */
@Import({Color.class, Red.class, MyImportSelecor.class, MyImportBeanDefinitionRegistrar.class})
@ComponentScan(value = "com.atguigu")
public class MainConfig {

    @Lazy
    @Bean("person")
    public Person person() {
        return new Person("jack", 21);
    }

    /**
     * conditional({condition}) 注解：按照一定条件进行判断，满足条件给容器中注册bean
     * 里面是一个condition数组
     */

    @Conditional({WindowsCondition.class})
    @Bean("bill")
    public Person person01() {
        return new Person("lucy", 34);
    }

    @Conditional({LinuxCondition.class})
    @Bean("linux")
    public Person person02() {
        return new Person("linux", 12);
    }

    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }

}
