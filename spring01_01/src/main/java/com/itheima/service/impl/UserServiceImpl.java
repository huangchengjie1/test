package com.itheima.service.impl;

import com.itheima.dao.IUserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.factory.BeanFactory;
import com.itheima.service.IUserService;

public class UserServiceImpl implements IUserService {

    //创建dao
   // private IUserDao userDao = new UserDaoImpl();

    //使用工厂解耦的方式创建dao
    private IUserDao userDao = BeanFactory.getBean("userdao",IUserDao.class);


    public void save() {
        userDao.save();
    }
}
