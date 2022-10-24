package com.buba.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-23
 * Time:20:27
 */

/**
 * 购物车对象
 */
public class Cart {
    // 购物车中购物项的数量
    private Integer totalCount;
    // 总商品金额
    private Double totalMoney;
    // 购物车商品 (key是book的id,value是商品信息)
    private List<CartItem> cartItems;

    public Cart() {
    }

    public Cart(Integer totalCount, Double totalMoney, List<CartItem> cartItems) {
        this.totalCount = totalCount;
        this.totalMoney = totalMoney;
        this.cartItems = cartItems;
    }

    public Integer getTotalCount() {
        totalCount = 0;
        if (cartItems != null && cartItems.size() > 0) {
            for (CartItem cartItem : cartItems) {
                totalCount += cartItem.getBuyCount();
            }
        }
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Double getTotalMoney() {
        totalMoney = 0.0;
        if (cartItems != null && cartItems.size() > 0) {
            BigDecimal bigDecimal = BigDecimal.valueOf(totalMoney);
            for (CartItem cartItem : cartItems) {
                bigDecimal = bigDecimal.add(BigDecimal.valueOf(cartItem.getMoney()));
            }
            totalMoney = bigDecimal.doubleValue();
        }
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + totalCount +
                ", totalMoney=" + totalMoney +
                ", cartItems=" + cartItems +
                '}';
    }
}
