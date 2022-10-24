package com.buba.entity;

/**
 * Author:SmallTiger
 * Date:2022-10-23
 * Time:20:40
 */

/**
 * 购物车商品项
 */
public class CartItem {
    private Integer id; // 购物车项id
    private Integer bookId;//图书编号
    private Integer userId; //用户id
    private String imgPath;//图书图片
    private String name;//图书名称
    private Integer buyCount;//数量
    private Double price;//单价
    private Double money;//总金额

    public CartItem() {
    }

    public CartItem(Integer id, Integer buyCount) {
        this.id = id;
        this.buyCount = buyCount;
    }

    public CartItem(Integer id, Integer bookId, Integer userId, String imgPath, String name, Integer buyCount, Double price, Double money) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.imgPath = imgPath;
        this.name = name;
        this.buyCount = buyCount;
        this.price = price;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", imgPath='" + imgPath + '\'' +
                ", name='" + name + '\'' +
                ", buyCount=" + buyCount +
                ", price=" + price +
                ", money=" + money +
                '}';
    }
}
