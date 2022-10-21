package com.buba.service.Impl;

import com.buba.dao.BookTypeDao;
import com.buba.dao.Impl.BookTypeDaoImpl;
import com.buba.entity.Book;
import com.buba.service.BookTypeService;

import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-20
 * Time:19:07
 */
public class BookTypeServiceImpl implements BookTypeService {
    private BookTypeDao bookTypeDao = new BookTypeDaoImpl();

    @Override
    public List<String> findOneLevel() {
        return bookTypeDao.findOneLevel();
    }

    @Override
    public List<String> findTwoLevel(String ParentName) {
        return bookTypeDao.findTwoLevel(ParentName);
    }

    @Override
    public List<String> findThreeLevel(String ParentName) {
        return bookTypeDao.findThreeLevel(ParentName);
    }

    @Override
    public List<Book> findBookByType(String bookType) {
        return bookTypeDao.findBookByType(bookType);
    }
}
