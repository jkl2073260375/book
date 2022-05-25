package com.book.test;

import com.book.dao.UserDao;
import com.book.pojo.User;
import com.book.dao.impl.UserDaoImpl;
import org.junit.Test;


public class UserDaoTest {
    UserDao userDao=new UserDaoImpl();
    @Test
    public void queryByUsername() {
        if(userDao.queryByUsername("root")==null){
            System.out.println("用户名可用!");
        }else{
            System.out.println("用户名已存在!");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"admin","kyh123","2562763019@qq.com")));
    }

    @Test
    public void queryByUsernameAndPassword() {
        if(userDao.queryByUsernameAndPassword("root","kyh123")==null){
            System.out.println("用户名或密码错误,登录失败");
        }else{
            System.out.println("查询成功！");
        }
    }
}