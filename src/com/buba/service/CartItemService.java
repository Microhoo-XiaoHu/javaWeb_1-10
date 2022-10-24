package com.buba.service;

import com.buba.entity.CartItem;

import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-24
 * Time:11:05
 */
public interface CartItemService {
    // 新增购物车项
    void addCartItem(CartItem cartItem);
    //修改特定的购物车项
    void updateCartItem(CartItem cartItem);
    // 查询购物车信息
    List<CartItem> findCartItem(String username);
    // 根据id删除购物车项
    int deleteCartItem(Integer id);
    // 清空购物车
    int clearCartItem(Integer userId);
}
