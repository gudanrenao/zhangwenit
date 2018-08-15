package com.zhangwen.learn.zhangwenit.api.merchant.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author by wcg on 16/3/17.
 */
@Entity
public class Merchant {

    @Id
    @GeneratedValue
    private Long id;

    private Integer sid;

    private Date createDate = new Date();

    private String merchantSid;

    /**
     * 所属城市合伙人ID
     */
    private Long pManagerId;

    private String location;

    private String name;

    private String picture;
    /**
     * 服务电话
     */
    private String phoneNumber;
    /**
     * 合作关系 0普通商户  1 联盟商户 2 虚拟商户 为天使合伙人创建默认自带商户
     */
    private Integer partnership;

    private Double lng = 0.0;

    private Double lat = 0.0;

    private String payee;
    /**
     * 状态0 代表未开启乐店 ,
     */
    private Integer state = 0;
    /**
     * 鼓励金收取权限 1=可用|0=不可使用
     */
    private Integer receiptAuth;

    /**
     * 是否开启优惠买单服务 1=开启|0=关闭
     */
    private Integer discount;
    /**
     * 是否开启储值卡功能 1=开启
     */
    private int valueCard;

    private String qrCodePicture; //商户收款码

    private String pureQrCode; //纯支付码
    /**
     * 会员绑定上线
     */
    private Long userLimit;
    /**
     * 佣金订单费率 如果是普通协议 该值 = ljBrokerage
     */
    private BigDecimal ljCommission;
    /**
     * 普通订单费率
     */
    private BigDecimal ljBrokerage = new BigDecimal(0);

    /**
     * 返a积分比 单位百分比  【导流订单红包】
     */
    private BigDecimal scoreARebate = new BigDecimal(0);

    /**
     * 联系人
     */
    private String contact;
    /**
     * 绑定电话
     */
    private String merchantPhone;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getMerchantSid() {
        return merchantSid;
    }

    public void setMerchantSid(String merchantSid) {
        this.merchantSid = merchantSid;
    }

    public Long getpManagerId() {
        return pManagerId;
    }

    public void setpManagerId(Long pManagerId) {
        this.pManagerId = pManagerId;
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

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public int getValueCard() {
        return valueCard;
    }

    public void setValueCard(int valueCard) {
        this.valueCard = valueCard;
    }

    public String getQrCodePicture() {
        return qrCodePicture;
    }

    public void setQrCodePicture(String qrCodePicture) {
        this.qrCodePicture = qrCodePicture;
    }

    public String getPureQrCode() {
        return pureQrCode;
    }

    public void setPureQrCode(String pureQrCode) {
        this.pureQrCode = pureQrCode;
    }

    public Long getUserLimit() {
        return userLimit;
    }

    public void setUserLimit(Long userLimit) {
        this.userLimit = userLimit;
    }

    public BigDecimal getLjCommission() {
        return ljCommission;
    }

    public void setLjCommission(BigDecimal ljCommission) {
        this.ljCommission = ljCommission;
    }

    public BigDecimal getLjBrokerage() {
        return ljBrokerage;
    }

    public void setLjBrokerage(BigDecimal ljBrokerage) {
        this.ljBrokerage = ljBrokerage;
    }

    public BigDecimal getScoreARebate() {
        return scoreARebate;
    }

    public void setScoreARebate(BigDecimal scoreARebate) {
        this.scoreARebate = scoreARebate;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMerchantPhone() {
        return merchantPhone;
    }

    public void setMerchantPhone(String merchantPhone) {
        this.merchantPhone = merchantPhone;
    }
}
