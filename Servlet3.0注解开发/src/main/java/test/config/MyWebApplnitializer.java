package test.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebApplnitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {   //获取根容器配置类(Spring的配置类),父容器
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {    //获取web容器的配置类(SpringMVC配置文件),子容器
        return new Class[]{WebAppConfig.class};
    }

    //获取DispatcherServlet的映射信息
    // /:拦截所有请求(包括静态资源),不包括*.jsp
    // /*:拦截所有请求,包括*.jsp页面
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
