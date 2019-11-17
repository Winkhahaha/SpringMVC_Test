package xaled.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    /*
    配置Spring的容器
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /*
    配置SpringMVC的工厂
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /*
    配置Servlet的路径
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
