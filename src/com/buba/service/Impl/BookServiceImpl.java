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
    public List<Book> limitFindBook(Integer pageNo) {
        return bookDao.limitFindBook(pageNo);
    }

    @Override
    public int findBookCount() {
        return bookDao.findBookCount();
    }
}
