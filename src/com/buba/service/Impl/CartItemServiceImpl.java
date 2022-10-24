package com.buba.service.Impl;

import com.buba.dao.CartItemDao;
import com.buba.dao.Impl.CartItemDaoImpl;
import com.buba.entity.CartItem;
import com.buba.service.CartItemService;

import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-24
 * Time:11:06
 */
public class CartItemServiceImpl implements CartItemService {
    private CartItemDao cartItemDao = new CartItemDaoImpl();
    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemDao.addCartItem(cartItem);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemDao.updateCartItem(cartItem);
    }


    @Override
    public List<CartItem> findCartItem(String username) {
        return cartItemDao.findCartItem(username);
    }

    @Override
    public int deleteCartItem(Integer id) {
        return cartItemDao.deleteCartItem(id);
    }

    @Override
    public int clearCartItem(Integer userId) {
        return cartItemDao.clearCartItem(userId);
    }
}
