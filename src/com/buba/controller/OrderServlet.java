package com.buba.controller;

import com.buba.entity.*;
import com.buba.service.CartItemService;
import com.buba.service.Impl.CartItemServiceImpl;
import com.buba.service.Impl.OrderDetailServiceImpl;
import com.buba.service.Impl.OrderServiceImpl;
import com.buba.service.Impl.UserServiceImpl;
import com.buba.service.OrderDetailService;
import com.buba.service.OrderService;
import com.buba.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Author:SmallTiger
 * Date:2022-10-25
 * Time:10:56
 */
@WebServlet("/order")
public class OrderServlet extends ViewBaseServlet {
    private OrderService orderService = new OrderServiceImpl();
    private UserService userService = new UserServiceImpl();
    private CartItemService cartItemService = new CartItemServiceImpl();
    private OrderDetailService orderDetailService = new OrderDetailServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        // 购物车点击去结账,跳转到结账页面,生成订单,并且生成订单详情
        if(req.getParameter("method").equals("checkout")){
            this.createOrder(req,resp);
            processTemplate("/pages/cart/checkout",req,resp);
        }
        // 查询我的订单
        if(req.getParameter("method").equals("myOrder")){
            this.myOrder(req,resp);
            processTemplate("/pages/order/order",req,resp);
        }
        // 查看详情
        if(req.getParameter("method").equals("seeOrderDetail")){
            this.seeDetail(req,resp);
            processTemplate("/pages/order/order_detail",req,resp);
        }
        // 退款,删除订单
        if(req.getParameter("method").equals("deleteOrder")){
            this.deleteOrder(req,resp);
        }
    }

    private void deleteOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        orderDetailService.deleteOrderDetail(Long.valueOf(id));
        orderService.deleteOrder(Long.valueOf(id));
        // 更新订单数据
        this.myOrder(req,resp);
        processTemplate("/pages/order/order",req,resp);
    }


    public void seeDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        List<OrderDetail> list = orderDetailService.findOrderDetail(Long.valueOf(id));
        req.setAttribute("orderDetail",list);
        Integer totalCount = 0;
        BigDecimal totalMoney = BigDecimal.valueOf(0.0);
        for (OrderDetail orderDetail : list) {
            totalCount += orderDetail.getBookCount();
            totalMoney = totalMoney.add(orderDetail.getAmount());
        }
        req.setAttribute("totalCount",totalCount);
        req.setAttribute("totalMoney",totalMoney);

        // 获取订单状态
        String status = req.getParameter("status");
        req.setAttribute("status",status);
        req.setAttribute("id",id);
    }

    private void myOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        // 获取用户名
        String username = (String)session.getAttribute("username");
        // 通过用户名查找user对象
        User user = userService.findUserByUsername(username);
        // 先查询到订单信息
        List<Order> list = orderService.findAllOrder(user);
        // list覆盖到session会话域
        session.setAttribute("myOrderList",list);
    }

    private void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        // 获取购物车信息
        Cart cart = (Cart) session.getAttribute("cart");
        // 获取用户名
        String username = (String)session.getAttribute("username");
        // 通过用户名查找user对象
        User user = userService.findUserByUsername(username);
        // 先查询到订单信息
        List<Order> list = orderService.findAllOrder(user);
        // 生成订单编号
        String uuid = get16UUID();
        // 循环存放订单信息的集合
        for (Order order : list) {
            // 判断生成的订单号,在订单中是否存在
            if(order.getOrderId().equals(uuid)){
                // 如果存在,那么再生成订单编号
                uuid = get16UUID();
            }
        }
        // 创建order对象,给属性赋值
        Order order = new Order();
        order.setOrderId(Long.valueOf(uuid)); // 订单id
        order.setOrderCount(cart.getTotalCount()); // 订单数量
        order.setOrderAmount(cart.getTotalMoney()); // 金额
        order.setOrderStatus(0); // 状态
        order.setUserId(user.getUserId()); // 用户id

        // 生成订单
        orderService.addOrder(order);
        // 生成订单详情
        for (CartItem cartItem : cart.getCartItems()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setBookId(cartItem.getBookId());
            orderDetail.setBookName(cartItem.getName());
            orderDetail.setBookCount(cartItem.getBuyCount());
            orderDetail.setAmount(cartItem.getMoney());
            orderDetail.setOrderId(order.getOrderId());
            orderDetailService.addOrderDetail(orderDetail);
        }

        // 清空购物车
        cartItemService.clearCartItem(user.getUserId());
        // 更新购物车
        cart.setCartItems(cartItemService.findCartItem(username));
        session.setAttribute("cart",cart);
        // orderId设置请求域
        req.setAttribute("orderId",order.getOrderId());

    }

    // 获取16位的订单编号
    public static String get16UUID(){
        // 1.开头两位，标识业务代码或机器代码（可变参数）
        String machineId = "11";
        // 2.中间四位整数，标识日期
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        String dayTime = sdf.format(new Date());
        // 3.生成uuid的hashCode值
        int hashCode = UUID.randomUUID().toString().hashCode();
        // 4.可能为负数
        if(hashCode < 0){
            hashCode = -hashCode;
        }
        // 5.算法处理: 0-代表前面补充0; 10-代表长度为10; d-代表参数为正数型
        String value = machineId + dayTime + String.format("%010d", hashCode);
        return value;
    }
}
