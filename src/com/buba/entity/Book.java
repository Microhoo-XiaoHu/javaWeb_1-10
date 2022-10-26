package com.buba.entity;

import java.math.BigDecimal;

/**
 * Author:SmallTiger
 * Date:2022-10-17
 * Time:09:03
 */
public class Book {
    private Integer bookId;
    private String name;
    private BigDecimal price;
    private String author;
    private Integer sales; // 销量
    private Integer stock; // 库存
    private String imgPath;

    public Book() {
    }

    public Book(Integer bookId, BigDecimal price, Integer sales, Integer stock) {
        this.bookId = bookId;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
    }

    public Book(Integer bookId, String name, BigDecimal price, String author, Integer sales, Integer stock, String imgPath) {
        this.bookId = bookId;
        this.name = name;
        this.price = price;
        this.author = author;
        this.sales = sales;
        this.stock = stock;
        this.imgPath = imgPath;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "BookDao{" +
                "bookId=" + bookId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
