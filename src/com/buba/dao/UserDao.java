package com.buba.dao;

import com.buba.entity.User;

/**
 * Author:SmallTiger
 * Date:2022-10-13
 * Time:10:33
 */
public interface UserDao {
    // 注册用户,添加用户
    int addUser(User user);

    // 查询,通过用户名和密码在数据库查询是否存在该用户
    int findUserByNameAndPassword(String userName, String userPassword);

    // 通过用户名查询数据库是否存在
    int findUserByName(String userName);

    // 通过用户名查询用户id
    User findUserByUsername(String username);
}
