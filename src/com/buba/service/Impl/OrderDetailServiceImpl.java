package com.buba.service.Impl;

import com.buba.dao.Impl.OrderDetailDaoImpl;
import com.buba.dao.OrderDetailDao;
import com.buba.entity.OrderDetail;
import com.buba.service.OrderDetailService;

import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-26
 * Time:08:57
 */
public class OrderDetailServiceImpl implements OrderDetailService {
    private OrderDetailDao orderDetailDao = new OrderDetailDaoImpl();
    @Override
    public int addOrderDetail(OrderDetail orderDetail) {
        return orderDetailDao.addOrderDetail(orderDetail);
    }

    @Override
    public int deleteOrderDetail(Long orderId) {
        return orderDetailDao.deleteOrderDetail(orderId);
    }

    @Override
    public List<OrderDetail> findOrderDetail(Long orderId) {
        return orderDetailDao.findOrderDetail(orderId);
    }
}
