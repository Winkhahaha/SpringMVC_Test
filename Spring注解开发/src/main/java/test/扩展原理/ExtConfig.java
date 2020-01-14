package test.扩展原理;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import test.entity.Blue;


/**
 * 扩展原理
 * BeanFactoryPostProcessor
 * 时机:
 * ioc容器创建对象
 * invokeBeanFactoryPostProcessor
 *
 * ApplicationListener:监听容器中发布的事件,事件驱动模型开发
 *步骤:
 * 1.写一个监听器(ApplicationListener的实现类),来监听某一个事件(ApplicationEvent及其子类)
 * 2.把监听器加入到容器中
 * 3.只要容器中有相关事件的发布,我们就监听到该事件
 * 4.如何发布事件?
 *
 * @EventListener(classes = ApplicationEvent.class)标注某方法监听事件
 *
 */
@ComponentScan("test.扩展原理")
@Configuration
public class ExtConfig {

    @Bean
    public Blue blue(){
        return new Blue();
    }


}
