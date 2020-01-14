package test.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//默认加载ioc容器中的组件,荣威首先会调用无参构造器创建对象,再进行初始化赋值操作
@Component
public class Boss {
    private Car car;

    @Autowired      //构造器要用的组件也是从容器中获取
    public Boss(Car car) {
        this.car = car;
        System.out.println("Boss的有参构造器被IOC调用!");
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }

    public Car getCar() {
        return car;
    }

    //@Autowired      //标注在方法上,Spring容器创建当前对象,就会调用方法,完成赋值,方法使用的参数自定义类型从ioc容器中获得
    public void setCar(Car car) {
        this.car = car;
    }
}
