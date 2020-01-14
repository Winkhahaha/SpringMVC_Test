package test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {
    //使用@Value赋值
    //1.基本数值
    //2.可以写SpEl,#{}
    //3.可以写${},取出配置文件(person.properties)的值
    @Value("张三")
    private String name;
    @Value("#{20-2}")
    private Integer age;
    @Value("${person.nickName}")
    private String nickName;

}
