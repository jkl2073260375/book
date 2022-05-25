package com.book.dao;

import com.book.pojo.Admin;

import java.util.List;

public interface AdminDao {
    public List<Admin> queryForAdmins();
    public Admin queryForAdmin(Integer id);

    public Admin queryByUsernameAndPassword(String username, String password);
}
