package test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import test.entity.Car;
import test.entity.Dog;

/**
 * bean的生命周期
 * 创建-->初始化-->销毁
 * 容器管理bean的生命周期
 * 我们可以自定义初始化和销毁方法,容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁方法
 *
 * 构造(对象创建)
 *  单实例:在容器启动的时候就创建对象
 *  多实例:在每次获取时创建对象
 *
 *  初始化:
 *      对象创建完成,并赋值好,调用初始化方法...
 *  销毁:
 *      单实例:bean在容器关闭时
 *      多实例:容器不会管理这个bean,容器不会调用destroy方法
 *
 *
 * 1.指定初始化销毁方法
 * 指定init-method和destroy-method
 *
 * 2.通过Bean实现InitializingBean(定义初始化逻辑)
 *              DisposableBean(定义销毁逻辑)
 *
 *3.可以使用JSR250:
 * @PostConstruct:在bean创建完成并且赋值完成后,来执行初始化方法
 * @PreDestroy:在容器销毁bean之前,通知我们清理bean
 *
 * 4.BeanPostProcessor:bean的后置处理器
 * postProcessBeforeInitialization:初始化之前调用
 * postProcessAfterInitialization:初始化之后调用
 * 遍历得到容器中所有的BeanPostProcessor,挨个执行BeforeInitialization,一旦返回null,跳出循环,不会执行后面的BeanPostProcessor
 */
@ComponentScan("test.entity")
@Configuration
public class MainConfigOfLifeCycle {

   @Bean(initMethod = "init",destroyMethod = "destroy")
    public Car car(){
       return new Car();
   }


}
