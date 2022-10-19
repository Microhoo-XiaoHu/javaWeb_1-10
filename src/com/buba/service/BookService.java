package com.buba.service;

import com.buba.entity.Book;

import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-17
 * Time:09:03
 */
public interface BookService {
    // 添加图书
    int addBook(Book book);
    // 删除图书
    int deleteBook(Integer bookId);
    // 修改图书
    int updateBook(Book book);
    // 查询所有图书
    List<Book> limitFindBook(Integer pageNo,Integer min,Integer max);
    // 查询图书总记录数的方法
    int findBookCount(Integer min,Integer max);
    // 通过图书id查找图书
    Book findBookById(Integer bookId);
    // 查找图书的最高价
    int maxPrice();
}
