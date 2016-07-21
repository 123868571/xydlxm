package com.paopao.hzgzf.modules.pay.entity;

import com.paopao.hzgzf.common.persistence.DataEntity;
/**
 * 销账记录
 * @author songyh
 *
 */
public class GzfBillRecord extends DataEntity<GzfBillRecord>{

	private static final long serialVersionUID = 1L;

	private Integer billingCycleId;

    private String accountId;

    private String paymentId;

    private String acctItemId;

    private Integer acctItemTypeId;

    private Double lastBillAmount;//销帐前金额

    private Double billAmount;//销帐金额
    
    private String balanceId;//帐本id

	public GzfBillRecord(){
    	super();
    }

    public GzfBillRecord(String id){
    	super(id);
    }
    
    public String getBalanceId() {
    	return balanceId;
    }
    
    public void setBalanceId(String balanceId) {
    	this.balanceId = balanceId;
    }
    
    public Integer getBillingCycleId() {
        return billingCycleId;
    }

    public void setBillingCycleId(Integer billingCycleId) {
        this.billingCycleId = billingCycleId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId == null ? null : paymentId.trim();
    }

    public String getAcctItemId() {
        return acctItemId;
    }

    public void setAcctItemId(String acctItemId) {
        this.acctItemId = acctItemId == null ? null : acctItemId.trim();
    }

    public Integer getAcctItemTypeId() {
        return acctItemTypeId;
    }

    public void setAcctItemTypeId(Integer acctItemTypeId) {
        this.acctItemTypeId = acctItemTypeId;
    }

    public Double getLastBillAmount() {
        return lastBillAmount;
    }

    public void setLastBillAmount(Double lastBillAmount) {
        this.lastBillAmount = lastBillAmount;
    }

    public Double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }
}