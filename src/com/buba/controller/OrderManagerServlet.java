package com.buba.controller;

import com.buba.entity.Order;
import com.buba.entity.User;
import com.buba.service.Impl.OrderManagerServiceImpl;
import com.buba.service.Impl.OrderServiceImpl;
import com.buba.service.Impl.UserServiceImpl;
import com.buba.service.OrderManagerService;
import com.buba.service.OrderService;
import com.buba.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-26
 * Time:10:48
 */
@WebServlet("/orderManager")
public class OrderManagerServlet extends ViewBaseServlet{
    private OrderManagerService orderManagerService = new OrderManagerServiceImpl();
    private UserService userService = new UserServiceImpl();
    private OrderServlet orderServlet = new OrderServlet();
    private OrderService orderService = new OrderServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        // 订单管理页面查询
        if(req.getParameter("method").equals("orderManager")){
            this.orderManager(req,resp);
            processTemplate("/pages/manager/order_manager",req,resp);
        }
        if(req.getParameter("method").equals("seeOrderDetail")){
            orderServlet.seeDetail(req,resp);
            processTemplate("/pages/manager/order_manager_detail",req,resp);
        }
        if(req.getParameter("method").equals("updateStatus")){
            this.updateStatus(req,resp);
        }
    }

    private void updateStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取订单id
        String id = req.getParameter("id");
        // 获取订单状态
        String status = req.getParameter("status");
        // 修改订单状态
        orderService.updateStatus(Integer.valueOf(status),Long.valueOf(id));
        // 查看订单
        orderServlet.seeDetail(req,resp);
        processTemplate("/pages/manager/order_manager_detail",req,resp);
    }

    private void orderManager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取会话域
        HttpSession session = req.getSession();
        // 设置当前页,默认值为1,目的为了显示首页
        Integer orderPageNo = 1;
        // 获取点击按钮的函数中路径地址传的pageNo值
        String pageNoStr = req.getParameter("pageNo");
        if(pageNoStr != null){
            orderPageNo = Integer.parseInt(pageNoStr);
        }
        // 将pageNo参数覆盖到session会话域中
        session.setAttribute("orderPageNo",orderPageNo);

        // 获取到数据库中总记录数,目的为了计算出总页数
        int orderCount = orderManagerService.findOrderCount();
        // 将总记录数覆盖到会话域
        session.setAttribute("orderCount",orderCount);
        // 总页数
        int orderPageCount = (orderCount+10-1)/10;

        // for循环总页数,目的为前端li标签渲染
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < orderPageCount; i++) {
            list.add(i+1);
        }
        session.setAttribute("orderListPage",list);
        // 将总页数覆盖到session会话域中
        session.setAttribute("orderPageCount",orderPageCount);

        // 查询全部订单,展示在订单管理页面
        List<Order> orderList = orderManagerService.findAllOrder(orderPageNo);
        session.setAttribute("orderList",orderList);
        // 如果数据库中有订单,那么通过id查到的用户都放到一个集合中,到前端页面循环获取用户名
        if(orderList != null && orderList.size() > 0){
            ArrayList<User> userList = new ArrayList<>();
            for (Order order : orderList) {
                // 通过id查用户
                User user = userService.findUserById(order.getUserId());
                userList.add(user);
            }
            req.setAttribute("userList",userList);
        }

    }
}
