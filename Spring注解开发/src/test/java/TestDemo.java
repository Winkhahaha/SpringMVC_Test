import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import test.aop.MathCalculator;
import test.config.*;
import test.config.tx.TXConfig;
import test.entity.*;
import test.service.BookService;
import test.service.UserService;
import test.扩展原理.ExtConfig;

import javax.sql.DataSource;
import java.util.Map;

public class TestDemo {

    @Test
    public void test01() {
        // 使用配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
    }

    @Test
    public void test02() {
        // 使用注解
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person = applicationContext.getBean(Person.class);
        System.out.println(person);
        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        for (String s : beanNamesForType) {
            System.out.println(s);  // 查看Person.class在容器中的id名:person
        }

    }

    @Test
    public void test03() {
        // 使用注解
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);   // 查看容器中有哪些组件
        }
    }

    @Test
    public void test04() {
        // 使用注解
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        System.out.println("IOC容器创建完成...");
        Object person = applicationContext.getBean("person");   //默认是单实例的
        Object person2 = applicationContext.getBean("person");
        System.out.println(person == person2);      //true
    }

    @Test
    public void test05() {
        // 使用注解
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        System.out.println("IOC容器创建完成...");
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        System.out.println(environment.getProperty("os.name")); //获取当前操作系统名
        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }
        Map<String, Person> persons = applicationContext.getBeansOfType(Person.class);
        System.out.println(persons);
    }

    @Test
    public void test06() {
        // 使用@import注解导入组件后查看
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);   // 查看容器中有哪些组件
        }
    }

    @Test
    public void test07() {
        // 使用BeanFactory导入组件后查看
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);   // 查看容器中有哪些组件
        }

        //工厂Bean获取的是调用getObject创建的对象
        Object color = applicationContext.getBean("colorFactory");
        Object color2 = applicationContext.getBean("colorFactory");
        // 加&前缀,获取到工厂bean本身
        Object color3 = applicationContext.getBean("&colorFactory");
        System.out.println("bean的类型:" + color.getClass());
        System.out.println("beanFactory的类型:" + color3.getClass());
        System.out.println(color == color2);
    }

    @Test
    public void test08() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("容器创建完成...");

        applicationContext.getBean("car");
        applicationContext.close();     //关闭容器,销毁容器内组件

    }

    @Test
    public void test09() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);
        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);   // 查看容器中有哪些组件
        }
        Object person = applicationContext.getBean("person");

        //加载完外部配置文件后保存到运行的环境变量中
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("person.nickName");
        System.out.println(property);
        System.out.println(person);

    }

    @Test
    public void test10() {
        //@Autowired
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        BookService service = applicationContext.getBean(BookService.class);
        System.out.println(service);
        applicationContext.close();


    }

    @Test
    public void test11() {
        //@Autowired
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        System.out.println("ioc容器创建完成");
        Boss boss = applicationContext.getBean(Boss.class);
        System.out.println(boss);
        Car car = applicationContext.getBean(Car.class);
        System.out.println(car);

        CEO ceo = applicationContext.getBean(CEO.class);
        System.out.println(ceo);
       // System.out.println(applicationContext);
        applicationContext.close();


    }

    @Test
    public void test12() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfProfile.class);
        System.out.println("ioc容器创建完成");

        String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }

        applicationContext.close();


    }

    @Test
    public void test13() {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //  设置当前环境
        applicationContext.getEnvironment().setActiveProfiles("test","dev");
        //  注册主配置类
        applicationContext.register(MainConfigOfProfile.class);
        //  启动刷新容器
        applicationContext.refresh();
        System.out.println("ioc容器创建完成");

        String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }

        Green green = applicationContext.getBean(Green.class);
        System.out.println(green);
        applicationContext.close();


    }

    @Test
    public void testAOP() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
        MathCalculator calculator = applicationContext.getBean(MathCalculator.class);
        calculator.div(1, 0);
        applicationContext.close();


    }

    @Test
    public void testTX() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TXConfig.class);
        System.out.println("ioc容器创建完成");

        UserService userService = applicationContext.getBean(UserService.class);
        userService.addUser();

        applicationContext.close();


    }

    @Test
    public void test扩展() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);
        System.out.println("ioc容器创建完成");

        applicationContext.close();
    }

    @Test
    public void testListener() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);
        System.out.println("ioc容器创建完成");
        applicationContext.publishEvent(new ApplicationEvent(new String("这是一个事件!")) {
        });
        applicationContext.close();
    }

}
