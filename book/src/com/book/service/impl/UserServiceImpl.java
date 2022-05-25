package com.book.service.impl;

import com.book.dao.UserDao;
import com.book.dao.impl.UserDaoImpl;
import com.book.pojo.User;
import com.book.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
       return userDao.queryByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
    @Override
    public boolean existsUsername(String username) {
         if(userDao.queryByUsername(username)==null){
             return false;
         }else
             return true;
    }
}
