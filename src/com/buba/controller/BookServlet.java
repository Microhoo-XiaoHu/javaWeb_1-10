package com.buba.controller;


import com.buba.entity.Book;
import com.buba.service.BookService;
import com.buba.service.Impl.BookServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Author:SmallTiger
 * Date:2022-10-13
 * Time:13:34
 */
public class BookServlet extends ViewBaseServlet {
    private BookService bookService = new BookServiceImpl();
    private IndexServlet indexServlet = new IndexServlet();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        // 添加图书
        if(req.getParameter("method").equals("addBook")){
            this.addBook(req,resp);
            processTemplate("/pages/manager/book_manager",req,resp);
        }
        // 跳转到图书管理页面
        if(req.getParameter("method").equals("book_manager")){
            indexServlet.limitFindBook(req,resp);
            HttpSession session = req.getSession();
            session.setAttribute("minPrice",null);
            session.setAttribute("maxPrice",null);
            processTemplate("/pages/manager/book_manager",req,resp);
        }
        // 修改图书界面
        if(req.getParameter("method").equals("editBook")){
            this.editBook(req,resp);
            processTemplate("/pages/manager/book_edit",req,resp);
        }
        // 修改图书方法
        if(req.getParameter("method").equals("updateBook")){
            this.updateBook(req,resp);
        }
        // 删除图书
        if(req.getParameter("method").equals("deleteBook")){
            this.deleteBook(req,resp);
        }

    }

    private void editBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Book book = bookService.findBookById(Integer.parseInt(id));
        req.setAttribute("book",book);

        // 获取页码,将其提升为session会话域,以便修改后提交时使用
        String pageNoStr = req.getParameter("pageNoStr");
        HttpSession session = req.getSession();
        session.setAttribute("pageNoStr",pageNoStr);

    }

    private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String pageNo = req.getParameter("pageNo");
        // 删除图书记录
        bookService.deleteBook(Integer.parseInt(id));

        // 获取图书最高价的值
        Double max = bookService.maxPrice();
        // 查找图书,获得集合,判断集合是否为空
        List<Book> books = bookService.limitFindBook(Integer.parseInt(pageNo),0, (int) Math.ceil(max));
        // 如果为空,那么页码-1
        if(books.isEmpty()){
            if(Integer.parseInt(pageNo) == 1){
                resp.sendRedirect("book?method=book_manager&pageNo=" + pageNo);
            }else{
                resp.sendRedirect("book?method=book_manager&pageNo=" + (Integer.parseInt(pageNo)-1));
            }
        }else{ // 如果不为空,那么保持在当前页
            resp.sendRedirect("book?method=book_manager&pageNo=" + pageNo);
        }
    }

    private void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String price = req.getParameter("price");
        String sales = req.getParameter("sales");
        String stock = req.getParameter("stock");
        Book book = new Book(Integer.parseInt(id),Double.valueOf(price),Integer.parseInt(sales),Integer.parseInt(stock));
        // 调用修改图书方法
        bookService.updateBook(book);
        // 获取修改页面传过来的,被提升为session会话域的页码值
        HttpSession session = req.getSession();
        Object pageNo = session.getAttribute("pageNoStr");

        // 重定向,修改成功后,回到当前修改的图书展示页面
        resp.sendRedirect("book?method=book_manager&pageNo=" + pageNo);
    }

    private void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(ServletFileUpload.isMultipartContent(req)){
            //创建工厂实现类FileItemFactory
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //创建用于解析上传数据的工具类
            ServletFileUpload servletFileUpload=  new ServletFileUpload(fileItemFactory );
            //解析上传的数据，得到每一个表单项FileItem

            try {
                List<FileItem> items  = servletFileUpload.parseRequest(req);

                FileItem fileItem = items.get(0);
                // 文件类型
                // 获取文件后缀名
                String imgtype = fileItem.getName().substring(fileItem.getName().lastIndexOf("."));
                // 给文件重新命名防止重复
                String imgName = UUID.randomUUID() + imgtype;
                System.out.println();
                String path="D:\\develop\\workspace\\javaWeb_1-10\\web\\static\\uploads\\";
                // 将上传的文件保存到服务器
                fileItem.write(new File(path, imgName));

                Book book = new Book();
                // 把服务器中的路径添加到数据库中
                String sqlPath=null;
                sqlPath = "static/uploads/" + imgName;
                book.setImgPath(sqlPath);
                book.setName(items.get(1).getString("UTF-8"));
                book.setPrice(Double.valueOf(items.get(2).getString("UTF-8")));
                book.setAuthor(items.get(3).getString("UTF-8"));
                book.setSales(Integer.valueOf(items.get(4).getString("UTF-8")));
                book.setStock(Integer.valueOf(items.get(5).getString("UTF-8")));
                System.out.println(book.toString());
                bookService.addBook(book);

                // 将参数覆盖到会话域,以便修改界面使用
                HttpSession session = req.getSession();
                session.setAttribute("ImgPath",book.getImgPath());
                session.setAttribute("Name",book.getName());
                session.setAttribute("Price",book.getPrice());
                session.setAttribute("Author",book.getAuthor());
                session.setAttribute("Sales",book.getSales());
                session.setAttribute("Stock",book.getStock());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
