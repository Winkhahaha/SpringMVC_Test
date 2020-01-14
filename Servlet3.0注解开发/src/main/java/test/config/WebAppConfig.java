package test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

//SpringMVC只扫描Controller,子容器
//useDefaultFilters = false:禁用默认规则
@Configuration
@ComponentScan(value = "test",includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})},useDefaultFilters = false)
@EnableWebMvc   //开启SpringMVC定制功能
public class WebAppConfig implements WebMvcConfigurer {

    // 配置视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //registry.jsp(); //默认所有的页面都从WEB-INF下找
        registry.jsp("/WEB-INF/views/",".jsp");
    }

    // 静态资源访问
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // 拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyFirstInterceptor()).addPathPatterns("/*");
    }
}
