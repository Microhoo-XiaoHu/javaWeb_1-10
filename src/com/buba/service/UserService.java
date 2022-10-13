package com.buba.service;

import com.buba.entity.User;

/**
 * Author:SmallTiger
 * Date:2022-10-13
 * Time:11:44
 */
public interface UserService {
    // 注册用户,添加用户
    int addUserDao(User user);

    // 查询,通过用户名和密码在数据库查询是否存在该用户
    int findUserByNameAndPassword(String userName, String userPassword);
}
