package com.paopao.hzgzf.modules.pay.entity;

import java.util.Date;

import com.paopao.hzgzf.common.persistence.DataEntity;
/**
 * 已销账账单
 * @author songyh
 *
 */
public class GzfAcctItemHis extends DataEntity<GzfAcctItemHis>{
	private static final long serialVersionUID = 1L;

	private String accountId;

    private Integer acctItemTypeId;

    private Integer billingCycleId;

    private Double receAmount;

    private Double factAmount;

    private Integer writeOffPri;
    
    private Date beginDate;//帐单费用开始日期
    
    private Date endDate;//帐单费用结束日期

    public GzfAcctItemHis(){
    	super();
    }

    public GzfAcctItemHis(String id){
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
    
}