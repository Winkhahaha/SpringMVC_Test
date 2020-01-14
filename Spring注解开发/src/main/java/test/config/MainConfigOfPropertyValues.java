package test.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import test.entity.Person;

// 使用@propertySource读取外部配置文件中的K-V,保存到运行的环境变量中,加载完外部配置文件后,使用${}取出配置文件的值
@PropertySource(value = {"classpath:/person.properties"})     //数据来源
@Configuration
public class MainConfigOfPropertyValues {

    @Bean
    public Person person() {
        return new Person();
    }
}
