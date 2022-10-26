package com.buba.dao.Impl;

import com.buba.dao.UserDao;
import com.buba.entity.User;
import com.buba.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Author:SmallTiger
 * Date:2022-10-13
 * Time:10:39
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
    @Override
    public int addUser(User user) {
        String sql = "insert into t_user(user_name,user_password,email) values(?,?,?)";
        int i = jdbcTemplate.update(sql, user.getUserName(), user.getUserPassword(), user.getEmail());
        return i;
    }

    @Override
    public int findUserByNameAndPassword(String userName, String userPassword) {
        String sql = "select count(*) from t_user where user_name = ? and user_password = ?";
        Integer i = jdbcTemplate.queryForObject(sql, Integer.class, userName, userPassword);
        return i;
    }

    @Override
    public int findUserByName(String userName) {
        String sql = "select count(*) from t_user where user_name = ?";
        Integer i = jdbcTemplate.queryForObject(sql, Integer.class, userName);
        return i;
    }

    @Override
    public User findUserByUsername(String username) {
        String sql = "select * from t_user where user_name = ?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        return user;
    }

    @Override
    public User findUserById(Integer id) {
        String sql = "select * from t_user where user_id = ?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        return user;
    }
}
