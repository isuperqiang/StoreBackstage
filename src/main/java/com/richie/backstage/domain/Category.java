package com.richie.backstage.domain;

import java.util.Date;

/**
 * @author richie on 2018.06.26
 * 商品分类
 */
public class Category {
    private int catId;
    // 名称
    private String name;
    // 创建时间
    private Date createdAt;
    // 排序
    private int sequence;
    private User user;

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Category{" +
                "catId=" + catId +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", sequence=" + sequence +
                ", user=" + user +
                '}';
    }
}
