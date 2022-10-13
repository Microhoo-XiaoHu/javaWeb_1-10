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
    public int addUserDao(User user) {
        return userDao.addUserDao(user);
    }

    @Override
    public int findUserByNameAndPassword(String userName, String userPassword) {
        return userDao.findUserByNameAndPassword(userName,userPassword);
    }
}
