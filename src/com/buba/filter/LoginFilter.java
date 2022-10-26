package com.buba.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)servletRequest;
        HttpServletResponse resp=(HttpServletResponse) servletResponse;
        //无论是否登录过,都要放行的资源   登录页  登录校验Controller 和一些静态资源
        String requestURI = req.getRequestURI();
        //放行主页和静态资源
        if(requestURI.contains("/index")|| requestURI.contains("/static/")){
            // 直接放行
            filterChain.doFilter(req,resp);
            // 后续代码不再执行
            return;
        }

        if(req.getParameter("user") != null){
            //放行登录校验方法
            if(req.getParameter("user").equals("login_success")){
                // 直接放行
                filterChain.doFilter(req,resp);
                // 后续代码不再执行
                return;
            }

            //放行注册时用户名是否存在的方法
            if(req.getParameter("user").equals("findUserByName")){
                // 直接放行
                filterChain.doFilter(req,resp);
                // 后续代码不再执行
                return;
            }

            //放行注册方法
            if(req.getParameter("user").equals("regist_success")){
                // 直接放行
                filterChain.doFilter(req,resp);
                // 后续代码不再执行
                return;
            }
        }

        if(req.getParameter("jump") != null){
            //放行登录界面
            if(req.getParameter("jump").equals("login")){
                // 直接放行
                filterChain.doFilter(req,resp);
                // 后续代码不再执行
                return;
            }

            //放行注册
            if(req.getParameter("jump").equals("regist")){
                // 直接放行
                filterChain.doFilter(req,resp);
                // 后续代码不再执行
                return;
            }
        }

        // 需要登录之后才能访问的资源,如果没登录,重定向到login上,提示用户进行登录
        HttpSession session = req.getSession();
        Object user = session.getAttribute("username");
        if(null != user){// 如果登录过 放行
            filterChain.doFilter(req,resp);
        }else{// 没有登录过,跳转至登录页
            resp.sendRedirect("/jumpHtml?jump=login");
        }
    }

    @Override
    public void destroy() {

    }
}
