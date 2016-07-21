package com.paopao.hzgzf.modules.pay.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.paopao.hzgzf.common.persistence.DataEntity;
/**
 * 余额收入支出明细entity
 * @author songyh
 *
 */
public class GzfBalanceRecord extends DataEntity<GzfBalanceRecord>{
	private static final long serialVersionUID = 1L;

    private String accountBalanceId;

    private String paymentId;

    private String accountId;

    private int balanceTypeId;

    private String operType;

    private String optCode;

    private Double amount;

    private Double balance;
    
    private Integer acctItemTypeId;
    
    private Integer spePaymentId;
    
    private Double totalBalance;//同一帐户同一spePaymentId总余额
    

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date beginTime;//开始时间
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;//结束时间，用于查询
    
    public Double getTotalBalance() {
    	return totalBalance;
    }
    
    public void setTotalBalance(Double totalBalance) {
    	this.totalBalance = totalBalance;
    }
    
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

	public GzfBalanceRecord(){
    	super();
    }
    
    public GzfBalanceRecord(String id){
    	super(id);
    }

    public String getAccountBalanceId() {
        return accountBalanceId;
    }

    public void setAccountBalanceId(String accountBalanceId) {
        this.accountBalanceId = accountBalanceId == null ? null : accountBalanceId.trim();
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId == null ? null : paymentId.trim();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public int getBalanceTypeId() {
        return balanceTypeId;
    }

    public void setBalanceTypeId(int balanceTypeId) {
        this.balanceTypeId = balanceTypeId;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType == null ? null : operType.trim();
    }

    public String getOptCode() {
        return optCode;
    }

    public void setOptCode(String optCode) {
        this.optCode = optCode == null ? null : optCode.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}