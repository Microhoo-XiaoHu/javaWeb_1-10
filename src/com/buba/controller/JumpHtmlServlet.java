package com.buba.controller;

import com.buba.dao.Impl.UserDaoImpl;
import com.buba.dao.UserDao;
import com.buba.entity.User;
import com.buba.service.Impl.UserServiceImpl;
import com.buba.service.UserService;
import com.buba.utils.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Author:SmallTiger
 * Date:2022-10-12
 * Time:11:30
 */
public class JumpHtmlServlet extends ViewBaseServlet {
    private UserService userService = new UserServiceImpl();
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
            this.findUserByNameAndPassword(req,resp);
        }
        // 注册
        if(req.getParameter("jump").equals("regist")){
            processTemplate("/pages/user/regist",req,resp);
        }
        // 注册成功
        if(req.getParameter("jump").equals("regist_success")){
            this.addUserDao(req,resp);
            processTemplate("/pages/user/regist_success",req,resp);
        }
        // 注销
        if(req.getParameter("jump").equals("logoff")){
            HttpSession session = req.getSession();
            session.setAttribute("username",null);

            processTemplate("index",req,resp);
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
    // 登录
    private void findUserByNameAndPassword(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 加密
        String encryptPassword = MD5Util.encrypt(password);
        int i = userService.findUserByNameAndPassword(username, encryptPassword);
        if(i == 1){ // 登陆成功
            HttpSession session = req.getSession();
            session.setAttribute("username",username);
            super.processTemplate("/pages/user/login_success",req,resp);
        }else{ // 登录失败
            super.processTemplate("/pages/user/login",req,resp);
        }
    }
    // 注册
    private void addUserDao(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        // 获取用户名
        String name = req.getParameter("name");
        // 获取密码
        String password = req.getParameter("password");
        // 加密
        String encryptPassword = MD5Util.encrypt(password);
        // 获取邮箱
        String email = req.getParameter("email");
        User user = new User(name, encryptPassword, email);
        // 验证码




        HttpSession session = req.getSession();
        session.setAttribute("username",name);

        userService.addUserDao(user);
    }
}
