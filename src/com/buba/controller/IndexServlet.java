package com.buba.controller;

import com.buba.entity.Book;
import com.buba.service.BookService;
import com.buba.service.Impl.BookServiceImpl;

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

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        this.limitFindBook(req,resp);
    }

    private void limitFindBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置当前页,默认值为1,目的为了显示首页
        Integer pageNo = 1;
        // 获取点击按钮的函数中路径地址传的pageNo值
        String pageNoStr = req.getParameter("pageNo");
        if(pageNoStr != null){
            pageNo = Integer.parseInt(pageNoStr);
        }
        // 将pageNo参数覆盖到session会话域中
        HttpSession session = req.getSession();
        session.setAttribute("pageNo",pageNo);
        // 获取到数据库中总记录数,目的为了计算出总页数
        int count = bookService.findBookCount();
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
        List<Book> books = bookService.limitFindBook(pageNo);
        session.setAttribute("books",books);

        super.processTemplate("index",req,resp);
    }

}
