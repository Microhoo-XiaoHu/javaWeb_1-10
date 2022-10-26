package com.buba.service;

import com.buba.entity.OrderDetail;

import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-26
 * Time:08:27
 */
public interface OrderDetailService {
    // 增订单详情
    int addOrderDetail(OrderDetail orderDetail);
    // 删除订单详情
    int deleteOrderDetail(Long orderId);
    // 查询订单详情
    List<OrderDetail> findOrderDetail(Long orderId);
}
