package com.book.test;

import com.book.dao.AdminDao;
import com.book.dao.impl.AdminDaoImpl;
import com.book.pojo.Admin;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AdminDaoImplTest {
    AdminDao adminDao=new AdminDaoImpl();
    @Test
    public void queryForAdmins() {
        List<Admin> adminList = adminDao.queryForAdmins();
        System.out.println(adminList);
    }

    @Test
    public void queryForAdmin() {
        Admin admin = adminDao.queryForAdmin(1);
        System.out.println(admin);
    }
}