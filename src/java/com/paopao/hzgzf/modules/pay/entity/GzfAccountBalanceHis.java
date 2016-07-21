package com.paopao.hzgzf.modules.pay.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.paopao.hzgzf.common.persistence.DataEntity;
/**
 * 余额帐本历史entity,余额为0时挪历史
 * @author songyh
 *
 */
public class GzfAccountBalanceHis extends DataEntity<GzfAccountBalanceHis>{

	private static final long serialVersionUID = 1L;

	private String accountId;

    private Integer balanceTypeId;//余额类型

    private Integer spePaymentId;//专款专用

    private Integer acctItemTypeId;//可用于销账的帐目

    private Double initBalance;

    private Double balance;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date effectDate;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date expireDate;
    
    private String state;
    
    private String paymentId;//缴费流水


    public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public GzfAccountBalanceHis(){
    	super();
    }
    
    public GzfAccountBalanceHis(String id){
    	super(id);
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public Integer getBalanceTypeId() {
        return balanceTypeId;
    }

    public void setBalanceTypeId(Integer balanceTypeId) {
        this.balanceTypeId = balanceTypeId;
    }

    public Integer getSpePaymentId() {
        return spePaymentId;
    }

    public void setSpePaymentId(Integer spePaymentId) {
        this.spePaymentId = spePaymentId;
    }

    public Integer getAcctItemTypeId() {
        return acctItemTypeId;
    }

    public void setAcctItemTypeId(Integer acctItemTypeId) {
        this.acctItemTypeId = acctItemTypeId;
    }

    public Double getInitBalance() {
        return initBalance;
    }

    public void setInitBalance(Double initBalance) {
        this.initBalance = initBalance;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}