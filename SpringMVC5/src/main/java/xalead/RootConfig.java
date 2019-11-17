package xalead;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import xalead.springmvc.exception.MyExceptionrResolver;

import javax.sql.DataSource;

@Configuration
@Import(WebConfig.class)
@EnableTransactionManagement
public class RootConfig {


    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/cms");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
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
