package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import test.dao.BookMapper;

import javax.annotation.Resource;

@Service
public class BookService {
    @Qualifier("bookMapper")         //明确指定要装配的组件
    @Autowired(required = false)        //指定为非必须装配属性(即可以先为null)
    //@Resource
    private BookMapper bookMapper;

    public void print(){
        System.out.println(bookMapper);
    }

    @Override
    public String toString() {
        return "BookService{" +
                "bookMapper=" + bookMapper +
                '}';
    }
}
