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
        String sql = "update t_book set price = ?, sales = ?, stock = ? where book_id = ?";
        int i = jdbcTemplate.update(sql,book.getPrice(),book.getSales(),book.getStock(),book.getBookId());
        return i;
    }

    @Override
    public List<Book> limitFindBook(Integer pageNo,Integer min,Integer max) {
        String sql = "select * from t_book where price between ? and ? limit ? , 10";
        List<Book> book = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class),min,max,(pageNo-1)*10);
        return book;
    }

    @Override
    public int findBookCount(Integer min,Integer max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Integer i = jdbcTemplate.queryForObject(sql, Integer.class,min,max);
        return i;
    }

    @Override
    public Book findBookById(Integer bookId) {
        String sql = "select * from t_book where book_id = ?";
        Book book = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), bookId);
        return book;
    }

    @Override
    public Double maxPrice() {
        String sql = "select max(price) from t_book";
        Double i = jdbcTemplate.queryForObject(sql, Double.class);
        return i;
    }
}
