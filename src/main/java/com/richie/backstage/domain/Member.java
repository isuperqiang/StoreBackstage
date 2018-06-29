package com.richie.backstage.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author richie on 2018.06.29
 * 会员
 */
public class Member implements Serializable {

    private static final long serialVersionUID = 2896396383891306246L;

    private int memberId;
    // 昵称
    private String nickname;
    // 性别
    private int gender;
    // 手机号
    private String phone;
    // 积分
    private int credit;
    // 卡号
    private String cardNo;
    // 创建日期
    private Date createdAt;
    // 下单数
    private int orderCount;

    private User user;

    private String formattedDate;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", nickname='" + nickname + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", credit=" + credit +
                ", cardNo='" + cardNo + '\'' +
                ", createdAt=" + createdAt +
                ", orderCount=" + orderCount +
                ", user=" + user +
                '}';
    }
}
