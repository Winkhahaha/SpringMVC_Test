package xaled.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import xaled.exception.ExceptionHandler;

import javax.servlet.annotation.MultipartConfig;

@Configuration
@ComponentScan("xaled.springmvc")
@MultipartConfig
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");//前缀
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public ExceptionHandler exceptionHandler(){
        return new ExceptionHandler();
    }

    @Bean(name="multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(50*1024*1024);
        return commonsMultipartResolver;

    }
}
