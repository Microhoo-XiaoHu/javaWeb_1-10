package com.buba.dao.Impl;

import com.buba.dao.OrderManagerDao;
import com.buba.entity.Order;
import com.buba.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-26
 * Time:10:34
 */
public class OrderManagerDaoImpl implements OrderManagerDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());

    @Override
    public List<Order> findAllOrder(Integer pageNo) {
        String sql = "select * from t_order limit ?,10";
        List<Order> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class), (pageNo-1)*10);
        return list;
    }

    @Override
    public int findOrderCount() {
        String sql = "select count(*) from t_order";
        Integer i = jdbcTemplate.queryForObject(sql, Integer.class);
        return i;
    }
}
