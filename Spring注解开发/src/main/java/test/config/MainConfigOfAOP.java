package test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import test.aop.LogAspect;
import test.aop.MathCalculator;

/**
 * AOP:在程序运行期间将某段代码切入到指定方法指定位置进行运行的编程方式
 * 定义日志切面类LogAspect,切面类里面的方法需要动态感知Math运行到哪里,然后选择切入
 *      通知方法:
 *          前置通知:@Before-->logStart,在目标方法div运行之前运行
 *          后置通知:@After-->LogEnd,在目标方法div运行结束之后运行(无论方法正常结束还是异常结束)
 *          返回通知:@AfterReturn-->LogReturn,在目标方法正常结束之后返回
 *          异常通知:@AfterThrowing-->LogException,在目标方法出现异常以后运行
 *          环绕通知:@Around-->动态代理,手动推进目标方法运行joinPoint.procced()
 *  给切面类的目标方法标注通知注解
 *  将切面类和业务逻辑类都加入容器中
 *  必须告诉spring哪个是切面类:给切面类上加一个注解-->@Aspect
 *  需要给配置类加@EnableAspectJAutoProxy(开启基于注解的AOP模式)
 *
 *  三步:
 *  1.将业务逻辑组件和切面类都加入容器中,告诉Spring哪个是切面类(@Aspect)
 *  2.在切面类上的每一个通知方法都标注对应的通知注解,告诉Spring何时何地切入运行
 *  3.开启基于注解的AOP模式:@EnableAspectJAutoProxy
 *
 *  AOP原理:看给容器中注册了什么组件,这个组件什么时候工作,组件的功能是什么
 *      @EnableAspectJAutoProxy?
 *          --@Import(AspectJAutoProxyRegistrar.class),给容器导入该组件
 *                  利用AspectJAutoProxyRegistrar给容器中注册bean
 *
 *   传入配置类,创建ioc容器
 *   注册配置类,调用refresh刷新容器
 *   registerBeanPostProcessors注册bean的后置处理器来方便拦截bean的创建
 *      先获取ioc容器已经定义了的需要创建对象的所有BeanPostProcessor
 *      给容器中加别的BeanPostProcessor
 *
 *      优先注册实现了PriorityOrdered接口的BeanPostProcessor
 *      在给容器中注册实现了Ordered接口的BeanPostProcessor
 *      注册没实现优先级接口的Bean
 *
 *      创建AOP对象
 *      postProcessAfterInitialization
 *      return wrapifNecessary(bean,beanName,cacheKey)
 *      1.获取当前bean(MathCalculator)所有的增强器(通知方法) Object[] specificIntercepts
 *              a.找到候选的所有增强器(找哪些方法是需要切入当前bean方法的)
 *              b.获取到能在bean中使用的增强器
 *              c.给增强器排序
 *      2.保存当前bean在advisedBean中
 *      3.如果当前bean需要增强,创建当前bean的代理对象
 *              a.获取所有增强方法
 *              b.保存在proxyFactory
 *              c.创建代理对象
 *                  JdkDynamicAopproxy:jdk的动态代理
 *                  ObjenesisCglibAopProxy:cglib的动态代理
 *      4.给容器中返回使用cglib代理的bean(增前了的代理对象)
 *      5.以后容器中获取到的就是这个组件的代理对象,执行目标方法时,代理对象就会执行通知方法的流程
 *
 *
 *
 */
@EnableAspectJAutoProxy     //开启AOP功能
@Configuration
public class MainConfigOfAOP {

    @Bean
    //业务逻辑类加入到容器中
    public MathCalculator mathCalculator(){
        return new MathCalculator();
    }

    @Bean
    public LogAspect aspect(){
        return new LogAspect();
    }
}
