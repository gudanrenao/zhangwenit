package com.zhangwen.learn.zhangwenit.elasticsearch.model;

import com.zhangwen.learn.zhangwenit.api.merchant.entity.MerchantUser;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangwen on 16/3/17.
 */
@Document(indexName = "lepluslife", type = "merchant")
public class MerchantInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private Integer sid;

    private String merchantSid;

    private String location;

    private String name;

    private String picture;

    private String phoneNumber;

    private Integer partnership;

    private Double lng = 0.0;

    private Double lat = 0.0;

    private String payee;
    /**
     * 乐店开启状态 1=已开启
     */
    private Integer state = 0;
    /**
     * 鼓励金收取权限 1=可收取
     */
    private Integer receiptAuth;
    /**
     * 是否开启储值卡功能 1=开启
     */
    private int valueCard;
    /**
     * 门店所属商户
     */
    private MerchantUser merchantUser;

    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getMerchantSid() {
        return merchantSid;
    }

    public void setMerchantSid(String merchantSid) {
        this.merchantSid = merchantSid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getPartnership() {
        return partnership;
    }

    public void setPartnership(Integer partnership) {
        this.partnership = partnership;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getReceiptAuth() {
        return receiptAuth;
    }

    public void setReceiptAuth(Integer receiptAuth) {
        this.receiptAuth = receiptAuth;
    }

    public int getValueCard() {
        return valueCard;
    }

    public void setValueCard(int valueCard) {
        this.valueCard = valueCard;
    }

    public MerchantUser getMerchantUser() {
        return merchantUser;
    }

    public void setMerchantUser(MerchantUser merchantUser) {
        this.merchantUser = merchantUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
