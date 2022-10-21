package com.buba.dao.Impl;

import com.buba.dao.BookTypeDao;
import com.buba.entity.Book;
import com.buba.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-20
 * Time:18:48
 */
public class BookTypeDaoImpl implements BookTypeDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());

    @Override
    public List<String> findOneLevel() {
        String sql = "select type_name from t_book_type where parent_id = 0";
        List<String> list = jdbcTemplate.queryForList(sql, String.class);
        return list;
    }

    @Override
    public List<String> findTwoLevel(String ParentName) {
        String sql = "select type_name from t_book_type where parent_id in \n" +
                "\t\t(select type_id from t_book_type where type_name = ?)";
        List<String> list = jdbcTemplate.queryForList(sql, String.class,ParentName);
        return list;
    }

    @Override
    public List<String> findThreeLevel(String ParentName) {
        String sql = "select type_name from t_book_type where parent_id in \n" +
                "\t(select type_id from t_book_type where parent_id in \n" +
                "\t\t(select type_id from t_book_type where type_name = ?));";
        List<String> list = jdbcTemplate.queryForList(sql, String.class,ParentName);
        return list;
    }

    @Override
    public List<Book> findBookByType(String bookType) {
        String sql = "select * from t_book where type_id in (select type_id from t_book_type where type_name = ?)";
        List<Book> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class),bookType);
        return list;
    }
}
