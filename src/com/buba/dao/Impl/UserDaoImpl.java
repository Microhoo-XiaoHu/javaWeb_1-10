package com.buba.dao.Impl;

import com.buba.dao.UserDao;
import com.buba.entity.User;
import com.buba.utils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Author:SmallTiger
 * Date:2022-10-13
 * Time:10:39
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
    @Override
    public int addUserDao(User user) {
        String sql = "";
        return 0;
    }

    @Override
    public int findUserByNameAndPassword(String userName, Integer userPassword) {
        return 0;
    }
}
