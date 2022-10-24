package com.buba.utils;

import com.buba.dao.Impl.BookTypeDaoImpl;
import com.buba.dao.Impl.CartItemDaoImpl;
import com.buba.dao.Impl.UserDaoImpl;
import com.buba.entity.Book;
import com.buba.entity.CartItem;
import com.buba.entity.User;
import com.buba.service.Impl.BookTypeServiceImpl;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TestJdbc {
    public static void main(String[] args) {
//        //创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDateSource());
//        //3.调用方法
//        String sql = "select sum(sal) from emp";
//        Double count = template.queryForObject(sql, Double.class);
//        System.out.println(count);

        // 给图书随机分配类型
//        String sql = "update t_book set type_id = ? where book_id = ?";
//        Random random = new Random();
//        for (int i = 0; i < 290; i++) {
//            int r = random.nextInt(119-31+1)+31;
//            System.out.println(r);
//            template.update(sql,r,i+352);
//        }

//        BookTypeDaoImpl bookTypeDao = new BookTypeDaoImpl();
//        List<Book> book = bookTypeDao.findBookByType("程序设计");
//        System.out.println(book);

//        CartItemDaoImpl cartItemDao = new CartItemDaoImpl();
//        List<CartItem> list = cartItemDao.findCartItem("xiaohu0214");
//        System.out.println(list);
        BigDecimal bigDecimal = BigDecimal.valueOf(0.2);
        for (int i = 0; i < 20; i++) {
            bigDecimal = bigDecimal.add(BigDecimal.valueOf(i+0.1));
        }
        System.out.println(bigDecimal);
    }

}
