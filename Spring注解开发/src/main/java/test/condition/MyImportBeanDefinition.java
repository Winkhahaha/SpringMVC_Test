package test.condition;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import test.entity.Rainbow;
        /**
        AnnotationMetadata:当前类的注解信息
        BeanDefinitionRegistry:BeanDefinition注册类
        把所有需要添加到容器中的bean,调用方法手工加入
         */
public class MyImportBeanDefinition implements ImportBeanDefinitionRegistrar {
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 判断当前IOC容器是否包含这个组件
        boolean red = registry.containsBeanDefinition("test.entity.Red");
        boolean blue = registry.containsBeanDefinition("test.entity.Blue");
        if (red && blue) {
            // 指定bean的定义信息,Bean的类型等...
            RootBeanDefinition beanDefinition = new RootBeanDefinition(Rainbow.class);
            //beanDefinition.setScope("");
            // 注册一个bean,指定bean名
            registry.registerBeanDefinition("rainBow", beanDefinition);
        }
    }
}
