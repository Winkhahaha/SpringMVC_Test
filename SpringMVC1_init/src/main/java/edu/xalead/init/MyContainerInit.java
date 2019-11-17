package edu.xalead.init;

import edu.xalead.servlet.MyServlet;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.Set;

public class MyContainerInit implements ServletContainerInitializer {
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        /**
         * <servlet>
         *     <servlet-name>MyServlet</servlet-name>
         *     <servlet-class>edu.xalead.servlet.MyServlet</servlet-class>
         *     <load-on-startup>1</load-on-startup>
         * </servlet>
         */
        ServletRegistration.Dynamic dynamicServlet=servletContext.addServlet("DispatcherServlet", DispatcherServlet.class);
        /**
         * <servlet-mapping>
         *     <servlet-name>MyServlet</servlet-name>
         *     <url-pattern>/aaa/bbb</url-pattern>
         * </servlet-mapping>
         */
        dynamicServlet.addMapping("*.action");
        dynamicServlet.setAsyncSupported(true);
        //执行顺序
        dynamicServlet.setLoadOnStartup(1);
        dynamicServlet.setInitParameter("contextConfigLocation","classpath:springmvc.xml");
    }
}
