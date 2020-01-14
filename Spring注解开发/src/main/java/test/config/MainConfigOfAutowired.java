package test.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import test.dao.BookMapper;
import test.entity.CEO;
import test.entity.Car;

/**
 * 自动装配:
 * Spring利用依赖注入(DI),完成对IOC容器中各个组件的依赖关系赋值
 * 1.@Autowired:自动注入
 * a.默认按照类型去容器中找对应的组件applicationContext.getBean(BookMapper.class);
 * b.如果有多个相同组件,再将属性名称作为组件的id去容器中查找applicationContext.getBean("bookMapper");
 * 或者使用@Qualifier("bookMapper2")指定要装配的组件的id(非属性名)
 * c.使用自动装配默认一定要将属性值赋好,或者使用@Autowired(required = false)指定为非必须赋值
 * d.@Primary:让Spring自动装配时,默认使用首选的bean
 * 注:优先级 @Qualifier > @Primary
 * <p>
 * 2.Spring还支持使用@Resource和@Inject(java规范的注解)
 *
 * @Resource:默认是按照组件的属性名称来装配,没有能支持@Primary等功能
 * @Inject:需要导入javax.inject包,和Autowired功能一样,但是没有required=false 区别:
 * @Autowired是Spring定义的,另外两个是java规范 3.@Autowired标注位置:
 * 方法上,@Bean+方法参数,参数从容器中获取,默认不写@Autowired,默认自动装配
 * 构造器,如果组件只有一个有参构造器,这个有参构造器的@Autowired可以省略,还是可以自动从容器中获取
 * 方法参数位置
 * 不管在哪放,都是从容器中获取组件
 *
 * 4.自定义容器想要使用Spring底层的组件,
 * 自定义实现xxxAware,在创建对象时会调用接口规定的方法
 * xxxAware:使用xxxProcessor;
 *      ApplicationContextAware --> ApplicationContextAwareProcessor
 */
@Configuration
@ComponentScan(value = {"test"})
public class MainConfigOfAutowired {

    @Primary    //每次自动装配首选此组件
    @Bean("bookMapper2")
    public BookMapper bookMapper() {
        BookMapper bookMapper = new BookMapper();
        bookMapper.setLable("2");
        return bookMapper;
    }

    /**
     * @param car
     * @return
     * @Bean标注的方法创建对象时,方法参数的值从容器中获取
     */
    @Bean
    public CEO ceo(/*@Autowired*/ Car car) {
        CEO ceo = new CEO();
        ceo.setCar(car);
        return ceo;
    }

}
