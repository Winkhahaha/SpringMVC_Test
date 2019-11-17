package xalead;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import xalead.springmvc.converter.ChannelConverter;
import xalead.springmvc.converter.DateToStringConverter;
import xalead.springmvc.converter.StringToDateConverter;

@Configuration
@ComponentScan(basePackages = "xalead.springmvc")
@EnableWebMvc   //代替<mvc:annotation-driven>
public class WebConfig implements WebMvcConfigurer {
    // 配置jsp视图解析器
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDateConverter());
        registry.addConverter(new DateToStringConverter());
        registry.addConverter(new ChannelConverter());
    }
}
