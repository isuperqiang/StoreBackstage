package com.richie.backstage.domain;

import java.io.Serializable;

/**
 * @author richie on 2018.06.25
 * 店铺
 */
public class Store implements Serializable {

    private static final long serialVersionUID = -5668580332970818722L;

    private int storeId;
    // 名称
    private String name;
    // logo
    private String logo = "";
    // 地址
    private String address;
    // 类目
    private String category;
    // 电话
    private String phone;
    // 创建时间
    private String createdAt;
    // 介绍
    private String description;
    // 均价
    private String avgPrice = "";
    // 营业时间开始
    private String saleFrom = "";
    // 营业时间结束
    private String saleTo = "";
    // 店家
    private User user;

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(String avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getSaleFrom() {
        return saleFrom;
    }

    public void setSaleFrom(String saleFrom) {
        this.saleFrom = saleFrom;
    }

    public String getSaleTo() {
        return saleTo;
    }

    public void setSaleTo(String saleTo) {
        this.saleTo = saleTo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeId=" + storeId +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", address='" + address + '\'' +
                ", category='" + category + '\'' +
                ", createdAt=" + createdAt +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", avgPrice=" + avgPrice +
                ", saleFrom='" + saleFrom + '\'' +
                ", saleTo='" + saleTo + '\'' +
                ", user=" + user +
                '}';
    }
}
