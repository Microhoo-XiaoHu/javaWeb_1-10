package com.buba.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author:SmallTiger
 * Date:2022-10-12
 * Time:11:30
 */
public class JumpHtmlServlet extends ViewBaseServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 请求转发跳转到 /WEB-INF/view/ 路径.html

        // 判断跳转路径
        // 登录
        if(req.getParameter("jump").equals("login")){
            processTemplate("/pages/user/login",req,resp);
        }
        // 登录成功
        if(req.getParameter("jump").equals("login_success")){
            processTemplate("/pages/user/login_success",req,resp);
        }
        // 注册
        if(req.getParameter("jump").equals("regist")){
            processTemplate("/pages/user/regist",req,resp);
        }
        // 注册成功
        if(req.getParameter("jump").equals("regist_success")){
            processTemplate("/pages/user/regist_success",req,resp);
        }
        // 购物车
        if(req.getParameter("jump").equals("cart")){
            processTemplate("/pages/cart/cart",req,resp);
        }
        // 图书管理
        if(req.getParameter("jump").equals("book_manager")){
            processTemplate("/pages/manager/book_manager",req,resp);
        }
        // 图书订单管理
        if(req.getParameter("jump").equals("order_manager")){
            processTemplate("/pages/manager/order_manager",req,resp);
        }
        // 添加图书
        if(req.getParameter("jump").equals("book_edit")){
            processTemplate("/pages/manager/book_edit",req,resp);
        }
        // 我的订单
        if(req.getParameter("jump").equals("order")){
            processTemplate("/pages/order/order",req,resp);
        }
    }
}
