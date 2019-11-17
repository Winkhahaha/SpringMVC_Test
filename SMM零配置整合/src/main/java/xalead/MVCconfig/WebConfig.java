package xalead.MVCconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import xalead.MVCconfig.converter.DateToStringConverter;
import xalead.MVCconfig.converter.StringToDateConverter;

/*
自定义的配置
 */
@Configuration
@ComponentScan(basePackages = "xalead")
@EnableWebMvc   //代替<mvc:annotation-driven>
public class WebConfig implements WebMvcConfigurer {
    // 配置jsp视图解析器
//    @Bean
//    public ViewResolver viewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/jsp/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }

    /*
    模板解析器:ITemplateResolver
     */
    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("utf-8");
        templateResolver.setOrder(1);

        templateResolver.setCacheable(false);
        return templateResolver;
    }

    /*
    模板引擎: SpringTemplateEngine
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }


    /*
    thymeleaf视图解析器
     */
    @Bean
    public ThymeleafViewResolver  thymeleafResolver() {
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(templateEngine());
        thymeleafViewResolver.setCharacterEncoding("utf-8");
        return thymeleafViewResolver;
    }




    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDateConverter());
        //registry.addConverter(new DateToStringConverter());
    }
}
