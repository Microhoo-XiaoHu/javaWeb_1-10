package com.buba.controller;

import com.buba.entity.Book;
import com.buba.service.BookService;
import com.buba.service.Impl.BookServiceImpl;
import com.buba.service.Impl.BookTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-09-30
 * Time:08:18
 */
public class IndexServlet extends ViewBaseServlet {
    private BookService bookService = new BookServiceImpl();
    private BookTypeServiceImpl bookTypeService = new BookTypeServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        // 侧行栏数据
        List bookType = this.findBookType();
        HttpSession session = req.getSession();
        session.setAttribute("bookTypeList",bookType);
        // 图书列表数据
        this.limitFindBook(req,resp);
        super.processTemplate("index",req,resp);

    }

    public void limitFindBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取会话域
        HttpSession session = req.getSession();

        // 查询价格最大值
        Double max = bookService.maxPrice();
        // 获取前端input传入的价格最大值.最小值
        String minPrice = req.getParameter("minPrice");
        String maxPrice = req.getParameter("maxPrice");
        // 判断价格区间的input框有没有值
        if(minPrice != null && maxPrice != null){
            session.setAttribute("minPrice",minPrice);
            session.setAttribute("maxPrice",maxPrice);
            this.findBook(Integer.parseInt(minPrice),Integer.parseInt(maxPrice),req,resp);
        }else{
            this.findBook(0, (int) Math.ceil(max),req,resp);
        }
    }

    public void findBook(Integer min,Integer max,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取会话域
        HttpSession session = req.getSession();
        // 设置当前页,默认值为1,目的为了显示首页
        Integer pageNo = 1;
        // 获取点击按钮的函数中路径地址传的pageNo值
        String pageNoStr = req.getParameter("pageNo");
        if(pageNoStr != null){
            pageNo = Integer.parseInt(pageNoStr);
        }
        // 将pageNo参数覆盖到session会话域中
        session.setAttribute("pageNo",pageNo);

        // 获取到数据库中总记录数,目的为了计算出总页数
        int count = bookService.findBookCount(min,max);
        // 将总记录数覆盖到会话域
        session.setAttribute("count",count);
        // 总页数
        int pageCount = (count+10-1)/10;

        // for循环总页数,目的为前端li标签渲染
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < pageCount; i++) {
            list.add(i+1);
        }
        session.setAttribute("listPage",list);
            /*
             总记录条数      总页数
            *   1             1
                10            1
                11            2
                20            2
                21            3
               count     (count+10-1)/10
            * */
        // 将总页数覆盖到session会话域中
        session.setAttribute("pageCount",pageCount);

        // 查询全部图书(分页),展示在首页
        List<Book> books = bookService.limitFindBook(pageNo,min,max);
        session.setAttribute("books",books);


    }

    // 首页侧行栏查询书籍数据
    public List findBookType(){
        ArrayList<Object> list1 = new ArrayList<>();
        List<String> oneLevel = bookTypeService.findOneLevel();

        for (String two : oneLevel) {
            ArrayList<Object> list2 = new ArrayList<>();
            List<String> twoLevel = bookTypeService.findTwoLevel(two);
            list2.add(two);
            list2.add(twoLevel);
            ArrayList<Object> list3 = new ArrayList<>();
            for (String three : twoLevel) {
                List<String> threeLevel = bookTypeService.findTwoLevel(three);
                list3.add(threeLevel);
            }
            list2.add(list3);
            list1.add(list2);
        }
        return list1;
    }
}
