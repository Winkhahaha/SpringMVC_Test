package test.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import test.entity.Person;


@Configuration  //告诉Spring这是一个配置类
@ComponentScan(value = "test",excludeFilters =             //指定自动扫描的包,排除指定的注解类不被注入容器
        {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {}),
        // @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {MyTypeFilter.class})     //自定义规则
        })
/*@ComponentScan(value = "test",includeFilters =             //只把指定的注解类放入容器
        {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})},useDefaultFilters = false)*/
/*@ComponentScans(value = {                   //添加多个ComponentScan扫描规则
        @ComponentScan(value = "test",excludeFilters =             //指定自动扫描的包,排除指定的注解类不被注入容器
                {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class, Service.class})}),
        @ComponentScan(value = "test",excludeFilters =             //指定自动扫描的包,排除指定的注解类不被注入容器
                {@ComponentScan.Filter(type = FilterType.ASPECTJ,classes = {Controller.class})})
})*/
/*
扫描规则FilterType:
FilterType.ANNOTATION,按照注解-->Service.class
FilterType.ASSIGNABLE_TYPE:按照给定类型-->BookService.class
FilterType.ASPECTJ:使用ASPECTJ表达式
FilterType.REGEX:使用正则规则
FilterType.CUSTOM:使用自定义规则-->implements TypeFilter

 */
public class MainConfig {       // 配置类等于配置文件
    @Bean("person")   //给容器中注册一个Bean,类型为返回值类型,id默认是用方法名作为id,或者在注解中写value作为id
    public Person person() {
        return new Person("李四", 21,"");
    }
}
