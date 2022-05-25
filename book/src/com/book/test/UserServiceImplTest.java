package com.book.test;

import com.book.pojo.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserServiceImplTest {
    UserService userService=new UserServiceImpl();
    @Test
    public void registUser() {
        userService.registUser(new User(null,"ld","ld123","www.baidu@com"));
        userService.registUser(new User(null,"ad","ad123","www.guge@com"));


    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"admin","kyh123","null")));
    }

    @Test
    public void existsUsername() {
        if(userService.existsUsername("xxf")){
            System.out.println("用户名已存在！");
        }else{
            System.out.println("用户名可用");
        }
    }
}