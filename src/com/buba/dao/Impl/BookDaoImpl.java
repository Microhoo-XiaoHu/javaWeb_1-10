package com.buba.dao.Impl;

import com.buba.dao.BookDao;
import com.buba.entity.Book;
import com.buba.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-17
 * Time:16:22
 */
public class BookDaoImpl implements BookDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(img_path,name,price,author,sales,stock) values(?,?,?,?,?,?)";
        int i = jdbcTemplate.update(sql, book.getImgPath(), book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock());
        return i;
    }

    @Override
    public int deleteBook(Integer bookId) {
        String sql = "delete from t_book where book_id = ?";
        int i = jdbcTemplate.update(sql, bookId);
        return i;
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update from t_book(img_path,name,price,author,sales,stock) set (?,?,?,?,?,?)";
        int i = jdbcTemplate.update(sql, book.getImgPath(),book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock());
        return i;
    }

    @Override
    public List<Book> limitFindBook(Integer pageNo) {
        String sql = "select * from t_book limit ? , 10";
        List<Book> book = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class),(pageNo-1)*10);
        return book;
    }

    @Override
    public int findBookCount() {
        String sql = "select count(*) from t_book";
        Integer i = jdbcTemplate.queryForObject(sql, Integer.class);
        return i;
    }
}
