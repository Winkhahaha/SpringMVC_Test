package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.dao.UserMapper;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional      //表示当前方法是一个事务
    public void addUser() {
        userMapper.insert();
        System.out.println("插入完成!");
        int i = 10 / 0;
    }
}
