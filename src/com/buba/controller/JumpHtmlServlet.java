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
        resp.setContentType("application/json;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        // 请求转发跳转到 /WEB-INF/view/ 路径.html

        // 判断跳转路径
        // 跳转到登录页面
        if(req.getParameter("jump").equals("login")){
            processTemplate("/pages/user/login",req,resp);
        }
        // 登录方法
        if(req.getParameter("jump").equals("login_success")){
            this.login(req,resp);
        }
        // 跳转到注册页面
        if(req.getParameter("jump").equals("regist")){
            processTemplate("/pages/user/regist",req,resp);
        }
        // 注册方法
        if(req.getParameter("jump").equals("regist_success")){
            this.registerUser(req,resp);
            processTemplate("/pages/user/login",req,resp);
        }
        // 注册时,判断用户名是否重复
        if(req.getParameter("jump").equals("findUserByName")){
            this.findUserByName(req,resp);
        }
        // 注销方法
        if(req.getParameter("jump").equals("logoff")){
            HttpSession session = req.getSession();
            session.setAttribute("username",null);

            processTemplate("index",req,resp);
        }
        // 跳转到购物车页面
        if(req.getParameter("jump").equals("cart")){
            processTemplate("/pages/cart/cart",req,resp);
        }
        // 跳转到图书管理页面
        if(req.getParameter("jump").equals("book_manager")){
            processTemplate("/pages/manager/book_manager",req,resp);
        }
        // 跳转到图书订单管理页面
        if(req.getParameter("jump").equals("order_manager")){
            processTemplate("/pages/manager/order_manager",req,resp);
        }
        // 跳转到添加图书页面
        if(req.getParameter("jump").equals("book_edit")){
            processTemplate("/pages/manager/book_edit",req,resp);
        }
        // 跳转到我的订单页面
        if(req.getParameter("jump").equals("order")){
            processTemplate("/pages/order/order",req,resp);
        }
    }

    //查询用户名是否存在
    private void findUserByName(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
        // 获取输入的用户名
        String username = req.getParameter("username");
        // 注册前,我先查找数据库中有没有这个用户名
        int i = userService.findUserByName(username);
        if(i == 1){
            // 说明有了 我就不添加了
            resp.getWriter().write("" + i);
        }
    }

    // 登录
    private void login(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 加密
        String encryptPassword = MD5Util.encrypt(password);
        int i = userService.findUserByNameAndPassword(username, encryptPassword);
        System.out.println(i);
        HttpSession session = req.getSession();
        if(i == 1){ // 登陆成功
            session.setAttribute("username",username);
            super.processTemplate("index",req,resp);
        }else{
            resp.getWriter().write("" + i);
        }
    }
    // 注册
    private void registerUser(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        // 获取用户名
        String name = req.getParameter("name");
        // 获取密码
        String password = req.getParameter("password");
        // 加密
        String encryptPassword = MD5Util.encrypt(password);
        // 获取邮箱
        String email = req.getParameter("email");
        User user = new User(name, encryptPassword, email);

        HttpSession session = req.getSession();
        session.setAttribute("username",name);

        int i = userService.addUser(user);

    }
}
