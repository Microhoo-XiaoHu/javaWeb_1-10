package com.buba.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Author:SmallTiger
 * Date:2022-10-20
 * Time:20:58
 */
public class LateralServlet extends ViewBaseServlet {
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
        processTemplate("/pages/lateral/lateral",req,resp);
    }
}
