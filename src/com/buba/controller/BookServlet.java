package com.buba.controller;


import com.buba.service.BookService;
import com.buba.service.Impl.BookServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-13
 * Time:13:34
 */
public class BookServlet extends ViewBaseServlet {
    private BookService bookService = new BookServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        if(req.getParameter("method").equals("addBook")){
            this.addBook(req,resp);
        }


    }

    private void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(ServletFileUpload.isMultipartContent(req)){
            //创建工厂实现类FileItemFactory
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //创建用于解析上传数据的工具类
            ServletFileUpload servletFileUpload=  new ServletFileUpload(fileItemFactory );
            //解析上传的数据，得到每一个表单项FileItem

            try {
                List<FileItem> list = servletFileUpload.parseRequest(req);
                //判断，每一项，是普通类型，还是上传文件
                for(FileItem fileItem : list){
                    if(fileItem.isFormField()){
                        //普通
                        //name:
                        fileItem.getFieldName();
                        //value:
                        fileItem.getString("UTF-8");

                    }else{
                        //name:
                        System.out.println(fileItem.getFieldName());
                        //文件名称：
                        System.out.println(fileItem.getName());
                        //上传地址
                        fileItem.write(new File("D:\\develop\\workspace\\javaWeb_1-10\\web\\static\\uploads" + fileItem.getName()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }



        }

    }


}
