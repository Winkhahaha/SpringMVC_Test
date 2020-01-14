package test.dao;


import lombok.Data;
import org.springframework.stereotype.Repository;

//名字默认是类名首字母小写
@Repository
@Data
public class BookMapper {
    private String lable = "1";

}
