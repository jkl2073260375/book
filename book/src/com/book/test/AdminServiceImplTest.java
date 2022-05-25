package com.book.test;

import com.book.dao.AdminDao;
import com.book.dao.impl.AdminDaoImpl;
import org.junit.Test;

public class AdminServiceImplTest {
    AdminDao adminDao=new AdminDaoImpl();
    @Test
    public void queryForAdmins() {
    }

    @Test
    public void login() {
        if(adminDao.queryByUsernameAndPassword("admin","123456")==null){
            System.out.println("用户名或密码错误,登录失败");
        }else{
            System.out.println("查询成功！");
        }

    }
}