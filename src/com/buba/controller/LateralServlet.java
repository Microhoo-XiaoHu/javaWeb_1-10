package com.buba.controller;

import com.buba.dao.Impl.BookTypeDaoImpl;
import com.buba.entity.Book;
import com.buba.service.BookTypeService;
import com.buba.service.Impl.BookTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-20
 * Time:20:58
 */

/**
 * 首页侧行栏数据渲染
 */
public class LateralServlet extends ViewBaseServlet {
    private BookTypeService bookTypeService = new BookTypeServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("method").equals("lateral")){
            this.lateral(req,resp);
        }
    }

    private void lateral(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String item3 = req.getParameter("item3");
        HttpSession session = req.getSession();
        session.setAttribute("item3",item3);

        // 通过类型查找数据
        List<Book> book = bookTypeService.findBookByType(item3);
        session.setAttribute("bookTypeList",book);

        processTemplate("/pages/lateral/lateral",req,resp);
    }
}
