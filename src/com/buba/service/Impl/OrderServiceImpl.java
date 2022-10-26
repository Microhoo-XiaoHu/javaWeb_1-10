package com.buba.service.Impl;

import com.buba.dao.Impl.OrderDaoImpl;
import com.buba.dao.OrderDao;
import com.buba.entity.Order;
import com.buba.entity.User;
import com.buba.service.OrderService;

import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-25
 * Time:11:14
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    @Override
    public int addOrder(Order order) {
        return orderDao.addOrder(order);
    }

    @Override
    public int deleteOrder(Long orderId) {
        return orderDao.deleteOrder(orderId);
    }

    @Override
    public List<Order> findAllOrder(User user) {
        return orderDao.findAllOrder(user);
    }

    @Override
    public int updateStatus(Integer status, Long orderId) {
        return orderDao.updateStatus(status,orderId);
    }
}
