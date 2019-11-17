package xalead.springmvc.service;

import xalead.springmvc.dao.UserDao;
import xalead.springmvc.entity.Channel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Transactional
    public void addUser(Channel c) {
        userDao.addChannel(c);
    }
}
