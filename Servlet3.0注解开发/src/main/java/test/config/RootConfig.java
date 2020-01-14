package test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

//Spring的容器不扫描Controller
@Configuration
@ComponentScan(value = "test",excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})})
public class RootConfig {
}
