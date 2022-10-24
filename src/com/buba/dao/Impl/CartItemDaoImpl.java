package com.buba.dao.Impl;

import com.buba.dao.CartItemDao;
import com.buba.entity.CartItem;
import com.buba.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-24
 * Time:10:46
 */
public class CartItemDaoImpl implements CartItemDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());

    @Override
    public void addCartItem(CartItem cartItem) {
        String sql = "insert into t_cart_item(book_id,buyCount,user_id) values(?,?,?)";
        int i = jdbcTemplate.update(sql, cartItem.getBookId(), cartItem.getBuyCount(), cartItem.getUserId());
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        String sql = "update t_cart_item set buyCount = ? where id = ?";
        int i = jdbcTemplate.update(sql, cartItem.getBuyCount(), cartItem.getId());
    }

    @Override
    public List<CartItem> findCartItem(String username) {
        String sql = "select  a.id ,s.book_id, img_path,name,buyCount,price,price*buyCount money from \n" +
                "\tt_book s, (select id,book_id,buyCount from t_cart_item where user_id in (select user_id from t_user where user_name = ?)) a\n" +
                "\twhere s.book_id = a.book_id";
        List<CartItem> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CartItem.class), username);
        return list;
    }

    @Override
    public int deleteCartItem(Integer id) {
        String sql = "delete from t_cart_item where id = ?";
        int i = jdbcTemplate.update(sql, id);
        return i;
    }

    @Override
    public int clearCartItem(Integer userId) {
        String sql = "delete from t_cart_item where user_id = ?";
        int i = jdbcTemplate.update(sql, userId);
        return i;
    }
}
