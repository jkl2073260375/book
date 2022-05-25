package com.book.service;

import com.book.pojo.Admin;

import java.util.List;

public interface AdminService {
    public List<Admin> queryForAdmins();

    public Admin login(Admin admin);
}
