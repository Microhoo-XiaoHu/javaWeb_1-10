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

        // 目的为了跳回本页面时,获取修改后的购物车数据
        HttpSession session = req.getSession();
        // 获取用户名
        String username = (String)session.getAttribute("username");
        // 创建购物车对象
        Cart cart = new Cart();
        // 通过用户名查找购物车项,然后修改购物车对象中的购物车项
        cart.setCartItems(cartItemService.findCartItem(username));
        // 将购物车覆盖到session会话域
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
        // 如果购物车不为空
        if(cart != null){
            // 那么通过用户名查找用户,需要使用用户的id,当加入购物车是,不存在该商品,则直接添加,需要创建购物车项
            User user = userService.findUserByUsername(username);
            // 创建一个CartItem对象
            CartItem item;
            // 循环购物车的购物车项
            for (CartItem cartItem : cart.getCartItems()) {
                // 判断是否存在该商品,如果购物车项中的图书id 等于 我前端点击加入购物传过来的图书id时,说明存在该图书
                if(cartItem.getBookId().equals(Integer.valueOf(bookId))){
                    // 那么将该购物车项赋值给空的购物车对象
                    item = cartItem;
                    // 修改它的数量,使其加1
                    item.setBuyCount(item.getBuyCount()+1);
                    // 再调用修改方法
                    cartItemService.updateCartItem(item);

                    // 然后修改购物车对象的购物车项,将其覆盖到session会话域
                    cart.setCartItems(cartItemService.findCartItem(username));
                    session.setAttribute("cart",cart);
                    // 点击添加购物车,重定向到首页
                    resp.sendRedirect("index");
                    return;
                }
            }
            // 此时说明购物车中不存在该商品项,所以创建新的购物车对象,赋值给上边空的购物车
            item = new CartItem();
            // 设置购物车的内容
            item.setBookId(Integer.parseInt(bookId));
            item.setUserId(user.getUserId());
            item.setBuyCount(1);
            // 调用添加方法
            cartItemService.addCartItem(item);

        }
        // 这是购物车中没有该商品信息时,之后修改购物车对象的购物车项,将购物车对象覆盖到session会话域
        cart.setCartItems(cartItemService.findCartItem(username));
        session.setAttribute("cart",cart);
        // 点击添加购物车,重定向到首页
        resp.sendRedirect("index");

    }
}
