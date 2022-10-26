package com.buba.controller;

import com.buba.entity.Cart;
import com.buba.entity.User;
import com.buba.service.CartItemService;
import com.buba.service.Impl.CartItemServiceImpl;
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
 * Date:2022-10-17
 * Time:14:28
 */
public class UserServlet extends ViewBaseServlet {
    private UserService userService = new UserServiceImpl();
    private CartItemService cartItemService = new CartItemServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        // 请求转发跳转到 /WEB-INF/view/ 路径.html

        // 登录方法
        if(req.getParameter("user").equals("login_success")){
            this.login(req,resp);
        }

        // 注册方法
        if(req.getParameter("user").equals("regist_success")){
            this.registerUser(req,resp);
            processTemplate("/pages/user/login",req,resp);
        }

        // 注册时,判断用户名是否重复
        if(req.getParameter("user").equals("findUserByName")){
            this.findUserByName(req,resp);
        }

        // 注销方法
        if(req.getParameter("user").equals("logout")){
            HttpSession session = req.getSession();
            session.setAttribute("username",null);

            processTemplate("index",req,resp);
        }

    }

    // 登录
    private void login(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 加密
        String encryptPassword = MD5Util.encrypt(password);
        int i = userService.findUserByNameAndPassword(username, encryptPassword);
        HttpSession session = req.getSession();
        if(i == 1){ // 登陆成功
            // 将用户名覆盖到session会话域
            session.setAttribute("username",username);
            // 创建购物车对象
            Cart cart = new Cart();
            // 通过用户名查找购物车项,将其赋值给购物车
            cart.setCartItems(cartItemService.findCartItem(username));
            // 将购物车覆盖到session会话域
            session.setAttribute("cart",cart);
            // 再跳转到首页
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

}
