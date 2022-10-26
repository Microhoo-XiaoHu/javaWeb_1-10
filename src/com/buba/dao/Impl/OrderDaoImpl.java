package com.buba.dao.Impl;

import com.buba.dao.OrderDao;
import com.buba.entity.Order;
import com.buba.entity.User;
import com.buba.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-25
 * Time:11:13
 */
public class OrderDaoImpl implements OrderDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());

    @Override
    public int addOrder(Order order) {
        String sql = "insert into t_order(order_id,order_count,order_amount,user_id,order_status) values(?,?,?,?,?)";
        int i = jdbcTemplate.update(sql, order.getOrderId(), order.getOrderCount(), order.getOrderAmount(), order.getUserId(), order.getOrderStatus());
        return i;
    }

    @Override
    public int deleteOrder(Long orderId) {
        String sql = "delete from t_order where order_id = ?";
        int i = jdbcTemplate.update(sql, orderId);
        return i;
    }

    @Override
    public List<Order> findAllOrder(User user) {
        String sql = "select * from t_order where user_id = ?";
        List<Order> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class),user.getUserId());
        return list;
    }

    @Override
    public int updateStatus(Integer status, Long orderId) {
        String sql = "update t_order set order_status = ? where order_id = ?";
        int i = jdbcTemplate.update(sql, status, orderId);
        return i;
    }
}
