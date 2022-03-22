package com.atguigu.config;


import com.atguigu.aop.LogAspects;
import com.atguigu.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *  AOP【动态代理的底层】:
 *      旨在程序运行期间动态地将某段代码切入到指定方法指定位置进行运行的编程方式
 *  1)导入aop模块：spring-aspects
 *  2)定义一个业务逻辑类（MathCalculator）:在业务逻辑运行的时候将日志进行打印（在方法之前，方法结束，方法运行异常）
 *  3)定义一个日志切面类（LogAspects）：切面里面的方法需要动态感知MathCalculator.div运行到哪里来了
 *         通知方法：
 *                前置通知(@Before)：
 *                后置通知(@After)：无论方法正常结束还是异常结束都调用
 *                异常通知()AfterThrowing：在目标方法发生异常之后执行
 *                返回通知(@AfterReturning)：在目标方法正常返回后执行
 *                环绕通知(@Around)：动态代理，手动推进目标方法运行（joinPoint.proceed()）
 * 4)给切面类的方法标注何时何地运行（标注通知注解）
 * 5)将切面类和业务逻辑类（目标方法所在类）都加入到容器中
 * 6)必须告诉spring哪一个类是切面类（给切面类加一个注解：@Aspect）
 * 7)给配置类加@EnableAspectJAutoProxy [开启基于注解的aop模式]
 *
 * 主要就是三步走：
 *    1)将业务逻辑组件和切面类都加入到容器中，告诉spring哪个是切面类（@Aspect）
 *    2)在切面类上的每一个通知方法标注通知注解，告诉spring何时何地运行（切入点表达式要写对）
 *    3)开启基于注解的aop模式（在配置类中添加->@EnableAspectJAutoProxy注解）
 *
 *
 * AOP原理：
 *      1)@EnableAspectJAutoProxy 是什么？
 *      内层中有一个@Import(AspectJAutoProxyRegistrar.class)注解: 给容器导入AspectJAutoProxyRegistrar
 *      利用AspectJAutoProxyRegistrar自定义给容器注册bean
 *
 *
 *
 */

@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAop {

    //将业务逻辑类加入到容器中
    @Bean
    public MathCalculator mathCalculator(){
        return new MathCalculator();
    }
    //切面类也加入到容器中
    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }
}
