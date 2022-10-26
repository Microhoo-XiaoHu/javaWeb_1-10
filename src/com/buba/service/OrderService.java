package com.buba.service;

import com.buba.entity.Order;
import com.buba.entity.User;

import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-25
 * Time:11:13
 */
public interface OrderService {
    // 去结账生成订单
    int addOrder(Order order);

    // 删除订单
    int deleteOrder(Long orderId);

    // 查看全部订单
    List<Order> findAllOrder(User user);

    // 通过id修改订单状态
    int updateStatus(Integer status, Long orderId);
}
