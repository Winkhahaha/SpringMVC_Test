package xalead.MVCconfig;


import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import xalead.exception.MyExceptionrResolver;

import javax.sql.DataSource;

@Configuration
@Import(WebConfig.class)
@EnableTransactionManagement
public class RootConfig {


    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    //配置SqlSessionFactoryBean
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        bean.setDataSource(dataSource());
        return bean;
    }

    //配置开启Mapper扫描
    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        configurer.setBasePackage("xalead.dao");
        return configurer;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public MyExceptionrResolver myExceptionrResolver(){
        return new MyExceptionrResolver();
    }

    @Bean("multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver(){
     CommonsMultipartResolver file = new CommonsMultipartResolver();
     file.setMaxUploadSize(1024*1024*5);    // 文件限制大小5mb
     return file;
    }
}
