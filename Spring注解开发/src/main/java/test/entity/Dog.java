package test.entity;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Dog implements ApplicationContextAware {
    private ApplicationContext applicationContext;  //定义ioc容器

    public Dog() {
        System.out.println("Dog construct...");
    }

    @PostConstruct  //对象创建并赋值之后调用
    public void init() {
        System.out.println("Dog PostConstruct...");
    }

    @PreDestroy     //容器移除对象之前
    public void destroy() {
        System.out.println("Dog PreDestroy...");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
