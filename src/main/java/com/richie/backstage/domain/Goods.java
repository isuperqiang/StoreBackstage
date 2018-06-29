package com.richie.backstage.domain;

import java.io.Serializable;

/**
 * @author richie on 2018.06.26
 * 商品
 */
public class Goods implements Serializable {

    private static final long serialVersionUID = -8472903693879713361L;

    private int goodsId;
    // 名称
    private String gname;
    // 规格
    private String specification;
    // 图片
    private String picture;
    // 价格
    private double price;
    // 销量
    private int saleVolume;
    // 库存
    private int stock;
    // 成本
    private double cost;
    // 在售
    private boolean onSale = true;
    // 创建时间
    private String createdAt;
    // 分类
    private Category category;
    // 店家
    private User user;

    private String formattedDate;

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSaleVolume() {
        return saleVolume;
    }

    public void setSaleVolume(int saleVolume) {
        this.saleVolume = saleVolume;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", name='" + gname + '\'' +
                ", specification='" + specification + '\'' +
                ", picture='" + picture + '\'' +
                ", price=" + price +
                ", saleVolume=" + saleVolume +
                ", stock=" + stock +
                ", cost=" + cost +
                ", createdAt=" + createdAt +
                ", onSale=" + onSale +
                ", category=" + category +
                ", user=" + user +
                '}';
    }
}
