package com.buba.service.Impl;

import com.buba.dao.Impl.UserDaoImpl;
import com.buba.dao.UserDao;
import com.buba.entity.User;
import com.buba.service.UserService;

/**
 * Author:SmallTiger
 * Date:2022-10-13
 * Time:13:51
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int findUserByNameAndPassword(String userName, String userPassword) {
        return userDao.findUserByNameAndPassword(userName,userPassword);
    }

    @Override
    public int findUserByName(String userName) {
        return userDao.findUserByName(userName);
    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }
}
