package com.buba.service;

import com.buba.entity.Book;

import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-20
 * Time:19:06
 */
public interface BookTypeService {
    // 查询一级类型
    List<String> findOneLevel();

    // 查询子类型
    List<String> findTwoLevel(String ParentName);

    // 查询孙子类型
    List<String> findThreeLevel(String ParentName);

    // 通过类型查询对应的图书
    List<Book> findBookByType(String bookType);
}
