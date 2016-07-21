package com.paopao.hzgzf.modules.pay.entity;

import java.util.Date;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 未销帐账单
 *
 * @author songyh
 */
public class GzfAcctItem extends DataEntity<GzfAcctItem> {
    private static final long serialVersionUID = 1L;

    private String accountId;//账户id

    private Integer acctItemTypeId;//帐目类型

    private Integer billingCycleId;//帐单周期 如201601

    private Double receAmount;//应收金额

    private Double factAmount;//实收金额

    private Integer writeOffPri;//销账优先级

    private Date beginDate;//帐单费用开始日期

    private Date endDate;//帐单费用结束日期

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    private Date beginTime;//开始时间，用于时间段查询

    private Date endTime;//结束时间,用于时间段查询

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public GzfAcctItem() {
        super();
    }

    public GzfAcctItem(String id) {
        super(id);
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public Integer getAcctItemTypeId() {
        return acctItemTypeId;
    }

    public void setAcctItemTypeId(Integer acctItemTypeId) {
        this.acctItemTypeId = acctItemTypeId;
    }

    public Integer getBillingCycleId() {
        return billingCycleId;
    }

    public void setBillingCycleId(Integer billingCycleId) {
        this.billingCycleId = billingCycleId;
    }

    public Double getReceAmount() {
        return receAmount;
    }

    public void setReceAmount(Double receAmount) {
        this.receAmount = receAmount;
    }

    public Double getFactAmount() {
        return factAmount;
    }

    public void setFactAmount(Double factAmount) {
        this.factAmount = factAmount;
    }

    public Integer getWriteOffPri() {
        return writeOffPri;
    }

    public void setWriteOffPri(Integer writeOffPri) {
        this.writeOffPri = writeOffPri;
    }


    public Date getStatisStartDate() {
        return statisStartDate;
    }

    public void setStatisStartDate(Date statisStartDate) {
        this.statisStartDate = statisStartDate;
    }

    public Date getStatisEndDate() {
        return statisEndDate;
    }

    public void setStatisEndDate(Date statisEndDate) {
        this.statisEndDate = statisEndDate;
    }

    private Date statisStartDate; // 统计开始时间
    private Date statisEndDate; // 统计结束时间

}
