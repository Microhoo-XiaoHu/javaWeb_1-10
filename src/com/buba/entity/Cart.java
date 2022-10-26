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
    private BigDecimal totalMoney;
    // 购物车商品 (key是book的id,value是商品信息)
    private List<CartItem> cartItems;

    public Cart() {
    }

    public Cart(Integer totalCount, BigDecimal totalMoney, List<CartItem> cartItems) {
        this.totalCount = totalCount;
        this.totalMoney = totalMoney;
        this.cartItems = cartItems;
    }

    public Integer getTotalCount() {
        // 默认总数量为0
        totalCount = 0;
        // 如果购物车项这个集合不为空,并且集合大小大于0时,
        if (cartItems != null && cartItems.size() > 0) {
            // 循环购物车项,让其中的购买数量累加,从而获得总数量
            for (CartItem cartItem : cartItems) {
                totalCount += cartItem.getBuyCount();
            }
        }
        // 否则的话不累加
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getTotalMoney() {
        // 默认总商品价为0.0
        totalMoney = BigDecimal.valueOf(0.0);
        // 如果购物车项集合为空,或者集合大小大于0
        if (cartItems != null && cartItems.size() > 0) {
            for (CartItem cartItem : cartItems) {
                // 将购物车项中的每个单价进行累加
                totalMoney = totalMoney.add(cartItem.getMoney());
            }
        }
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
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
