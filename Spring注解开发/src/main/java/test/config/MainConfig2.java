package test.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import test.condition.LinuxCondition;
import test.condition.MyImportBeanDefinition;
import test.condition.MyImportSelector;
import test.condition.WinCondition;
import test.entity.Color;
import test.entity.ColorFactory;
import test.entity.Person;
import test.entity.Red;

@Configuration
//@Conditional({WinCondition.class})      //满足当前条件,这给类中配置的所有bean注册才能生效
//@Import(Color.class)        //导入组件,容器会自动注册该组件,id默认是组件的全类名
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinition.class})
public class MainConfig2 {

    @Bean("person")     //默认是单实例
    @Scope()
    /* @see
       ConfigurableBeanFactory#SCOPE_PROTOTYPE  多实例,ioc容器启动时不会调用方法创建对象放入容器,只有每次获取时才会创建一个,这样多次创建的对象就不同
	 * @see ConfigurableBeanFactory#SCOPE_SINGLETON  单实例,ioc容器请求会先调用方法创建对象放入容器,以后每次用就直接拿这一个对象即可
	 * @see org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST     同一次请求创建一个实例
	 * @see org.springframework.web.context.WebApplicationContext#SCOPE_SESSION     同一个session创建一个对象
	 * @see #value
	 *
	 * 单实例懒加载,启动时不创建,在第一次获取时再创建对象
	 */
    //@Lazy
    public Person person() {
        System.out.println("给容器添加Person王五...");
        return new Person("王五", 87,"");
    }

    /*
        @Condition:按照一定条件进行判断,满足条件给容器中注册bean
        如果系统是windows,给容器注册王五
        如果Linux系统,给容器注册linus
     */
    @Conditional({WinCondition.class})
    @Bean("哈哈Win")
    public Person person02(){
        return new Person("哈哈Win",62,"");
    }

    @Conditional({LinuxCondition.class})
    @Bean("linus")
    public Person person03(){
        return new Person("linus",50,"");
    }

    @Bean
    public ColorFactory colorFactory(){
        return new ColorFactory();
    }
}
