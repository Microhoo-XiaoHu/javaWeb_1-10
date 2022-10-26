package com.buba.entity;

import java.math.BigDecimal;

/**
 * Author:SmallTiger
 * Date:2022-10-25
 * Time:19:23
 */
public class OrderDetail {
    private Integer itemId;
    private Integer bookId;
    private String bookName;
    private Integer bookCount;
    private BigDecimal amount;
    private Long orderId;

    public OrderDetail() {
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "itemId=" + itemId +
                ", bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookCount=" + bookCount +
                ", amount=" + amount +
                ", orderId=" + orderId +
                '}';
    }
}
