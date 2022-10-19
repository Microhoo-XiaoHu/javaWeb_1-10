package com.buba.service.Impl;

import com.buba.dao.BookDao;
import com.buba.dao.Impl.BookDaoImpl;
import com.buba.entity.Book;
import com.buba.service.BookService;

import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-17
 * Time:18:31
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public int addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public int deleteBook(Integer bookId) {
        return bookDao.deleteBook(bookId);
    }

    @Override
    public int updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public List<Book> limitFindBook(Integer pageNo,Integer min,Integer max) {
        return bookDao.limitFindBook(pageNo,min,max);
    }

    @Override
    public int findBookCount(Integer min,Integer max) {
        return bookDao.findBookCount(min,max);
    }

    @Override
    public Book findBookById(Integer bookId) {
        return bookDao.findBookById(bookId);
    }

    @Override
    public int maxPrice() {
        return bookDao.maxPrice();
    }
}
