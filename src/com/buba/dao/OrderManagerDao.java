package com.buba.dao;

import com.buba.entity.Order;

import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-26
 * Time:10:07
 */
public interface OrderManagerDao {
    // 后台管理查询订单
    List<Order> findAllOrder(Integer pageNo);
    // 查询订单总记录数
    int findOrderCount();

    //
}
