package com.paopao.hzgzf.modules.pay.entity;

import com.paopao.hzgzf.common.persistence.DataEntity;
/**
 * 转帐entity
 * @author songyh
 *
 */
public class GzfAcctTransfer extends DataEntity<GzfAcctTransfer>{
	private static final long serialVersionUID = 1L;

    private String srcAcctId;//源帐户

    private String destAcctId;//目标帐户

    private Integer srcSpecPaymentId;//源帐户费用类型

    private Integer destSpecPaymentId;//目标帐户费用类型

    private String optCode;//操作编码

    private String srcBalanceId;//源帐户帐本id

    private String destBalanceId;//目标帐户帐本id

    private Double amount;//金额，以分为单位
    
    private Double totalFreeFee;//帐户余额
    
    private String phoneNo;
    
    private String custId;
    
    private String externalTransferId;//外部转帐编码
    
    public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public GzfAcctTransfer(){
    	super();
    }
    
    public GzfAcctTransfer(String id){
    	super(id);
    }
    
    public Double getTotalFreeFee() {
		return totalFreeFee;
	}

	public void setTotalFreeFee(Double totalFreeFee) {
		this.totalFreeFee = totalFreeFee;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSrcAcctId() {
        return srcAcctId;
    }

    public void setSrcAcctId(String srcAcctId) {
        this.srcAcctId = srcAcctId == null ? null : srcAcctId.trim();
    }

    public String getDestAcctId() {
        return destAcctId;
    }

    public void setDestAcctId(String destAcctId) {
        this.destAcctId = destAcctId == null ? null : destAcctId.trim();
    }

    public Integer getSrcSpecPaymentId() {
        return srcSpecPaymentId;
    }

    public void setSrcSpecPaymentId(Integer srcSpecPaymentId) {
        this.srcSpecPaymentId = srcSpecPaymentId;
    }

    public Integer getDestSpecPaymentId() {
        return destSpecPaymentId;
    }

    public void setDestSpecPaymentId(Integer destSpecPaymentId) {
        this.destSpecPaymentId = destSpecPaymentId;
    }

    public String getOptCode() {
        return optCode;
    }

    public void setOptCode(String optCode) {
        this.optCode = optCode == null ? null : optCode.trim();
    }

    public String getSrcBalanceId() {
        return srcBalanceId;
    }

    public void setSrcBalanceId(String srcBalanceId) {
        this.srcBalanceId = srcBalanceId == null ? null : srcBalanceId.trim();
    }

    public String getDestBalanceId() {
        return destBalanceId;
    }

    public void setDestBalanceId(String destBalanceId) {
        this.destBalanceId = destBalanceId == null ? null : destBalanceId.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}
    
	public String getExternalTransferId() {
		return externalTransferId;
	}
	
	public void setExternalTransferId(String externalTransferId) {
		this.externalTransferId = externalTransferId;
	}
}