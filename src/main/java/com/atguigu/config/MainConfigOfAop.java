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
 * AOP原理（所有的原理都是看给容器中注册了什么组件，这些组件什么时候工作？又有什么功能？）：
 * 研究@EnableAspectJAutoProxy注解：
 *      1)@EnableAspectJAutoProxy 是什么？
 *      内层中有一个@Import(AspectJAutoProxyRegistrar.class)注解: 给容器导入AspectJAutoProxyRegistrar
 *      利用AspectJAutoProxyRegistrar自定义给容器注册bean
 *      internalAutoProxyCreator =AnnotationAwareAspectJAutoProxyCreator
 *      这一步大概就是给容器中注册一个叫：
 *                    AnnotationAwareAspectJAutoProxyCreator的组件（注解装配模式的面向切面编程的自动代理创建器 ）
 *     2)这个创建器有什么功能？
 *         继承关系：
 *             AnnotationAwareAspectJAutoProxyCreator
 *             parent ->AspectJAwareAdvisorAutoProxyCreator
 *               parent ->AbstractAdvisorAutoProxyCreator
 *                parent ->AbstractAutoProxyCreator
 *                (implements ->SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware)(实现了bean的后置处理器)
 *                  后置处理器：在bean的初始化完成前后做事情，上面的接口还自动装配了beanFactory
 *           AbstractAutoProxyCreator实现的方法：
 *                a.setBeanFactory();
 *                b.以及后置处理器的逻辑(postProcess开头的一些方法);
 *          AbstractAdvisorAutoProxyCreator实现的方法：
 *                a.重写了父类AbstractAutoProxyCreator的setBeanFactory方法
 *                     重写的方法里面会调一个initBeanFactory方法
 *         AspectJAwareAdvisorAutoProxyCreator:并没有后置处理器的逻辑
 *          AnnotationAwareAspectJAutoProxyCreator:
 *               a.重写了父类中调用的initBeanFactory方法
 *
 * 创建流程：
 *        1)传入配置类，创建IOC容器,然后调用这个方法
 *        2)public AnnotationConfigApplicationContext(Class<?>... componentClasses) {
 *  * 		                this();
 *  * 		                register(componentClasses);
 *  * 		                 refresh();
 *  *        }
 *              注册配置类，调用refresh()刷新容器
 *         3)refresh中有一个registerBeanPostProcessors(beanFactory);方法，用来注册后置处理器，在容器创建前后
 *           进行操作
 *               怎么创建后置处理器呢？
 *                  a.先获取IOC容器中已经定义了的所有需要创建的beanPostProcessor
 *                  b.给容器中加入别的beanPostProcessor
 *                  c.过程中，优先注册实现PriorityOrdered接口的BeanPostProcessor
 *                  d.第二再来注册实现ordered接口的beanPostProcessor
 *                  e.最后再注册没有实现优先级接口的beanPostProcessor
 *                  f.如果获取不到，创建beanPostProcessor对象，注册beanPostProcessor并保存在容器中
 *                      也就是f这样一步，创建了internalAutoProxyCreator的beanPostProcessor
 *                        1)创建bean实例
 *                        2)populateBean:给bean的各种属性赋值
 *                        3)initializeBean:初始化bean
 *                          初始化bean的流程：
 *                              a.invokeAwareMethods():处理Aware接口的方法回调
 *                              b.applyBeanPostProcessorsBeforeInitialization:应用后置处理器的初始化方法：postProcessBeforeInitialization()
 *                              c.invokeInitMethods():执行自定义初始化方法
 *                              d.applyBeanPostProcessorsAfterInitialization:应用后置处理器的初始化方法：postProcessAfterInitialization()
 *                         4)BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功，并且调用了aspectJAdvisorsBuilder方法
 *                  g.把beanPostProcessor注册到BeanFactory中
 *                          beanFactory.addBeanPostProcessor(PostProcessor)
 *
 * =====================以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程=========================
 * AnnotationAwareAspectJAutoProxyCreator是InstantiationAwareBeanPostProcessor类型的后置处理器（实现了这个接口，即这个接口的实现类）
 *        4)finishBeanFactoryInitialization(beanFactory),完成beanFactory的初始化工作，创建剩下的单实例bean
 *             具体过程：
 *                a.遍历获取容器中所有的bean，依次创建对象(用getBean(beanName)方法)
 *                     getBean->doGetBean()->getSingleton()在这个方法中执行以下过程：
 *                        1，(先从缓存中获取当前bean，如果能获取到说明是被创建过的，就直接使用进行后续包装，否则再创建，这样保证了单实例的bean只能被创建一次，创建好的bean都会被缓存起来)
 *                        2，createBean()
 *                      注释：{
 *                      AnnotationAwareAspectJAutoProxyCreator会在任何bean创建之前先尝试bean实例（因为它是一个后置处理器，会对bean进行拦截）
 *                      【BeanPostProcessor是bean对象创建完成初始化前后调用的】
 *                        【InstantiationAwareBeanPostProcessor实在创建bean实例之前先尝试使用后置处理器返回对象的】}
 *                             其中的方法有：
 *                              1）resolveBeforeInstantiation(beanName,mbdToUse);解析BeforeInstantiation
 *                                    希望后置处理器返回一个代理对象，如果能返回就使用，如果不能就继续
 *                                       a.后置处理器先尝试返回一个对象
 *                                          先让bean调用applyBeanPostProcessorsBeforeInitialization
 *                                          拿到所有后置处理器，如果是InstantiationAwareBeanPostProcessor就执行后置处理器的PostProcessBeforeInstantiation方法
 *                                            if (bean != null{bean=applyBeanPostProcessorsAfterInitialization(params);}
 *
 *                              2)doCreateBean(beanName,mbdToUse,args)真正的去创建一个bean实例以上步骤3.6的流程是一样的
 *
 *                b.
 *
 *
 *
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
