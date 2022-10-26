package com.buba.dao.Impl;

import com.buba.dao.OrderDetailDao;
import com.buba.entity.OrderDetail;
import com.buba.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-26
 * Time:08:32
 */
public class OrderDetailDaoImpl implements OrderDetailDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());

    @Override
    public int addOrderDetail(OrderDetail orderDetail) {
        String sql = "insert into t_order_detail(book_id,book_name,book_count,amount,order_id) values(?,?,?,?,?)";
        int i = jdbcTemplate.update(sql, orderDetail.getBookId(), orderDetail.getBookName(), orderDetail.getBookCount(), orderDetail.getAmount(), orderDetail.getOrderId());
        return i;
    }

    @Override
    public int deleteOrderDetail(Long orderId) {
        String sql = "delete from t_order_detail where order_id = ?";
        int i = jdbcTemplate.update(sql, orderId);
        return i;
    }

    @Override
    public List<OrderDetail> findOrderDetail(Long orderId) {
        String sql = "select * from t_order_detail where order_id = ?";
        List<OrderDetail> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrderDetail.class), orderId);
        return list;
    }
}
