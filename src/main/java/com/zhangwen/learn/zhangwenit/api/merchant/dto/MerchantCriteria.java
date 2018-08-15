package com.zhangwen.learn.zhangwenit.api.merchant.dto;

/**
 * @author  by zhangwen
 */
public class MerchantCriteria {

    private Integer partnership;

    private String merchant;

    private int currPage;

    private int pageSize;

    private Long merchantType;

    private Long city;

    private Integer storeState;

    private Integer receiptAuth;

    private String start;

    private String end;

    private String merchantName;

    private String merchantSid;

    private Long partner;

    private Long salesStaff;

    public Long getPartner() {
        return partner;
    }

    public void setPartner(Long partner) {
        this.partner = partner;
    }

    public Long getSalesStaff() {
        return salesStaff;
    }

    public void setSalesStaff(Long salesStaff) {
        this.salesStaff = salesStaff;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStoreState() {
        return storeState;
    }

    public void setStoreState(Integer storeState) {
        this.storeState = storeState;
    }

    public Integer getReceiptAuth() {
        return receiptAuth;
    }

    public void setReceiptAuth(Integer receiptAuth) {
        this.receiptAuth = receiptAuth;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Long getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Long merchantType) {
        this.merchantType = merchantType;
    }

    public Integer getPartnership() {
        return partnership;
    }

    public void setPartnership(Integer partnership) {
        this.partnership = partnership;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getMerchantSid() {
        return merchantSid;
    }

    public void setMerchantSid(String merchantSid) {
        this.merchantSid = merchantSid;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }


}
