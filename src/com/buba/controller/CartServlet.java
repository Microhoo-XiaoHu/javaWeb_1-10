package com.buba.controller;

import com.buba.entity.Cart;
import com.buba.entity.CartItem;
import com.buba.entity.User;
import com.buba.service.CartItemService;
import com.buba.service.Impl.CartItemServiceImpl;
import com.buba.service.Impl.UserServiceImpl;
import com.buba.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Author:SmallTiger
 * Date:2022-10-23
 * Time:19:49
 */
@WebServlet("/cart")
public class CartServlet extends ViewBaseServlet {
    private CartItemService cartItemService = new CartItemServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        if (req.getParameter("method").equals("addCart")) {
            this.addCart(req,resp);
        }
        if(req.getParameter("method").equals("updateBuyCount")){
            this.updateBuyCount(req,resp);
        }
        if(req.getParameter("method").equals("deleteCartItem")){
            this.deleteCartItem(req,resp);
        }
        if(req.getParameter("method").equals("clear")){
            this.clear(req,resp);
        }

        // 目的为了跳回本页面,获取修改后的购物车数据
        HttpSession session = req.getSession();
        String username = (String)session.getAttribute("username");
        Cart cart = new Cart();
        cart.setCartItems(cartItemService.findCartItem(username));
        session.setAttribute("cart",cart);

        processTemplate("/pages/cart/cart",req,resp);
    }

    private void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String)session.getAttribute("username");
        // 通过用户名查找用户
        User user = userService.findUserByUsername(username);
        // 清空购物车
        cartItemService.clearCartItem(user.getUserId());
    }

    private void deleteCartItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取购物车项id
        String id = req.getParameter("id");
        cartItemService.deleteCartItem(Integer.parseInt(id));
    }

    private void updateBuyCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String buyCount = req.getParameter("buyCount");
        CartItem item = new CartItem(Integer.parseInt(id),Integer.parseInt(buyCount));
        cartItemService.updateCartItem(item);
    }

    /**
     * 首页点击加入购物车方法
    * */
    private void addCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 将指定的图书添加到当前用户购物车中
        HttpSession session = req.getSession();
        // 获取用户购物车
        Cart cart = (Cart)session.getAttribute("cart");
        // 获取用户名
        String username = (String)session.getAttribute("username");
        // 获取图书id
        String bookId = req.getParameter("bookId");
        if(cart != null){
            User user = userService.findUserByUsername(username);
            CartItem item;
            for (CartItem cartItem : cart.getCartItems()) {
                if(cartItem.getBookId().equals(Integer.valueOf(bookId))){
                    item = cartItem;
                    item.setBuyCount(item.getBuyCount()+1);
                    cartItemService.updateCartItem(item);
                    cart.setCartItems(cartItemService.findCartItem(username));
                    session.setAttribute("cart",cart);
                    // 点击添加购物车,重定向到首页
                    resp.sendRedirect("index");
                    return;
                }
            }

            item = new CartItem();
            item.setBookId(Integer.parseInt(bookId));
            item.setUserId(user.getUserId());
            item.setBuyCount(1);
            cartItemService.addCartItem(item);

        }
        cart.setCartItems(cartItemService.findCartItem(username));
        session.setAttribute("cart",cart);
        // 点击添加购物车,重定向到首页
        resp.sendRedirect("index");

    }
}
