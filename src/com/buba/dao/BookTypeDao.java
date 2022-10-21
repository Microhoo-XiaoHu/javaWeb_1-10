package com.buba.dao;

import com.buba.entity.Book;

import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-20
 * Time:18:44
 */
public interface BookTypeDao {

    // 查询一级类型
    List<String> findOneLevel();

    // 查询子类型
    List<String> findTwoLevel(String ParentName);

    // 查询孙子类型
    List<String> findThreeLevel(String ParentName);

    // 通过类型查询对应的图书
    List<Book> findBookByType(String bookType);
}
