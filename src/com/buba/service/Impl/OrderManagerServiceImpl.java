package com.buba.service.Impl;

import com.buba.dao.Impl.OrderManagerDaoImpl;
import com.buba.dao.OrderManagerDao;
import com.buba.entity.Order;
import com.buba.service.OrderManagerService;

import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-26
 * Time:10:46
 */
public class OrderManagerServiceImpl implements OrderManagerService {
    private OrderManagerDao orderManagerDao = new OrderManagerDaoImpl();

    @Override
    public List<Order> findAllOrder(Integer pageNo) {
        return orderManagerDao.findAllOrder(pageNo);
    }

    @Override
    public int findOrderCount() {
        return orderManagerDao.findOrderCount();
    }
}
