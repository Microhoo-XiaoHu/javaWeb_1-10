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

        // 跳转到注册页面
        if(req.getParameter("jump").equals("regist")){
            processTemplate("/pages/user/regist",req,resp);
        }

        // 跳转到购物车页面
        if(req.getParameter("jump").equals("cart")){
            processTemplate("/pages/cart/cart",req,resp);
        }

        // 跳转到添加图书页面
        if(req.getParameter("jump").equals("book_add")){
            processTemplate("/pages/manager/book_add",req,resp);
        }

    }

}
