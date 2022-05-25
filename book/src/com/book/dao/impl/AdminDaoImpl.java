package com.book.dao.impl;

import com.book.pojo.Admin;

import java.util.List;

public class AdminDaoImpl extends BaseDao implements com.book.dao.AdminDao {
    @Override
    public List<Admin> queryForAdmins() {
        String sql="select `id`,`username`,`password`,`email` from t_admin";
        List<Admin> adminList = queryForList(Admin.class, sql);
        return adminList;
    }

    @Override
    public Admin queryForAdmin(Integer id) {
        String sql="select `id`,`username`,`password`,`email` from t_admin where `id`=?";
        Admin admin = queryForOne(Admin.class, sql, id);
        return admin;
    }

    @Override
    public Admin queryByUsernameAndPassword(String username, String password) {
        String sql="select `id`,`username`,`password`,`email` from t_admin where `username`=? and `password`=?";
        Admin admin = queryForOne(Admin.class, sql, username, password);
        return admin;
    }
}
