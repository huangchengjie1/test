package com.itheima;

import com.itheima.factory.BeanFactory;
import com.itheima.service.IUserService;
import com.itheima.service.impl.UserServiceImpl;

public class App {

    public static void main(String[] args) {

        //创建service
       /* IUserService userService = new UserServiceImpl();
        userService.save();*/

       //创建对象（controller）
      IUserService userService1 = BeanFactory.getBean("userservice",IUserService.class);
     IUserService  userService2 =  BeanFactory.getBean("userservice",IUserService.class);
      userService1.save();

      //false
        System.out.println(userService1 == userService2 );

    }


}
