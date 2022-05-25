package com.book.dao;

import com.book.pojo.User;

public interface UserDao {
    public User queryByUsername(String username);
    public int saveUser(User user);
    public User queryByUsernameAndPassword(String username,String password);

}
