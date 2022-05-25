package com.book.service.impl;

import com.book.dao.AdminDao;
import com.book.dao.impl.AdminDaoImpl;
import com.book.pojo.Admin;
import com.book.service.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    AdminDao adminDao=new AdminDaoImpl();
    @Override
    public List<Admin> queryForAdmins() {
        List<Admin> adminList = adminDao.queryForAdmins();
        return adminList;
    }

    @Override
    public Admin login(Admin admin) {
        return adminDao.queryByUsernameAndPassword(admin.getUsername(),admin.getPassword());
    }
}
