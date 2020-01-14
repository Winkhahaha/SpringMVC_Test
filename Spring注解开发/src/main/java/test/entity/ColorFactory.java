package test.entity;

import org.springframework.beans.factory.FactoryBean;

//  创建Spring定义的FactoryBean
public class ColorFactory implements FactoryBean<Color> {
    // 返回一个Color对象,这个对象会添加到容器中
    public Color getObject() throws Exception {
        System.out.println("getObject被执行了...");
        return new Color();
    }

    public Class<?> getObjectType() {
        return Color.class;
    }

    // 是否单实例
    public boolean isSingleton() {
        return false;   // 多实例
    }
}
