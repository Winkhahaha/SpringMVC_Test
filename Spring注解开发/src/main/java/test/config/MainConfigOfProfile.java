package test.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import test.entity.Green;

import javax.sql.DataSource;

/**
 * @Profile:
 * Spring提供的可以根据当前环境,动态激活和切换一系列组件的功能
 *  开发环境,测试环境,生产环境
 *  指定组件在哪个环境的情况下才能被注册到组件中
 *  1.加了环境标识的bean,只有这个环境被激活的时候才能加入到容器,默认是default环境
 *  使用命令行参数:在虚拟机参数位置加上-Dspring.Profiles,active=test
 *  2.标注在配置类上,只有是指定的环境时候,整个配置类里面的所有配置才能开始生效
 *  3.没有标注环境标识的bean在任何环境下都是加载的
 */
@Profile("test")
@PropertySource("classpath:/jdbc.properties")
@Configuration
public class MainConfigOfProfile {

    @Value("${db.user}")
    private String username;

    @Bean
    public Green green(){
        return new Green();
    }

    @Profile("test")
    @Bean("Test")
    public DataSource dataSourceTest(@Value("${db.password}") String pwd){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername(username);
        dataSource.setPassword(pwd);
        return dataSource;
    }

    @Profile("dev")
    @Bean("Dev")
    public DataSource dataSourceDev(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Profile("prod")
    @Bean("Prod")
    public DataSource dataSourceProd(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }
}
