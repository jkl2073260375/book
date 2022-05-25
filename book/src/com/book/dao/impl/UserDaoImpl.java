package com.book.dao.impl;

import com.book.dao.UserDao;
import com.book.pojo.User;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryByUsername(String username) {
        String sql="select `id`,`username`,`password`,`email` from t_user where `username`=?";
        User user = queryForOne(User.class, sql, username);
        return user;
    }

    @Override
    public int saveUser(User user) {
        String sql="insert into t_user(`username`,`password`,`email`)value(?,?,?)";
        int i = update(sql, user.getUsername(), user.getPassword(), user.getEmail());
        return i;
    }

    @Override
    public User queryByUsernameAndPassword(String username, String password) {
        String sql="select `id`,`username`,`password`,`email` from t_user where `username`=? and `password`=?";
        User user = queryForOne(User.class, sql, username,password);
        return user;
    }
}
