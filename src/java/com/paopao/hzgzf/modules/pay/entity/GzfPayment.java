package com.paopao.hzgzf.modules.pay.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paopao.hzgzf.common.persistence.DataEntity;
import com.paopao.hzgzf.common.utils.excel.annotation.ExcelField;
/**
 * 缴费entity
 * @author songyh
 *
 */
public class GzfPayment extends DataEntity<GzfPayment>{
	private static final long serialVersionUID = 1L;

	private String accountBalanceId;

	@NotNull
    private String accountId;

    private String externalSeq;//外部流水

    private String priPayLogId;//返销对应缴费记录

    private String bankId;//银行编码

	private String paymentType;//缴费类型 0按帐户缴费；    1按用户帐单结清费用；

    @NotNull
    private String payMethod;//付费类型 1-前台收现金 2.银行代收

    private String certificateType;//凭证类型：    1-银行缴费凭证编号',

    private String certificateCode;//凭据编号
    
    private String photo;//凭据图片

    private Double oweFee;//欠费金额
    @NotNull
    private Double amount;//缴费金额

    private Integer balanceTypeId;//余额类型id

    @NotNull
    private Integer spePaymentId;//专款专用id

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date effectDate;//生效日期

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date expireDate;//失效日期

    private String state;//状态 0 : 正常    1 : 正在冲正    2 : 已被冲正

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date payDate;//缴费日期
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date beginTime;//开始时间
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;//结束时间，用于查询
    
    private String paymentOrderId;
    
    private Integer acctItemTypeId;
    
    private String amountYuan;
    
    private String payMethodName;
    
    private String effectDateStr;
    
    private String optCode;//操作编码 1 : 缴费    2 : 冲正     3 : 退费
    
    private String transferId;//转帐id
    
    private String channelType;//渠道类型
    
    private String phoneNo;
    
    private String orderCode;//流水号 生成规律：User.loginName+yyyymmddhhmmmss
    
    private String extendSpecPaymentId;//为多个帐目缴费时附带的放入此属性中，以|分隔
    
    private String extendAmount;//以|分隔
    
    private String custId;
    
    public GzfPayment(){
    	super();
    }
    
    public GzfPayment(String id){
    	super(id);
    }
    
    @ExcelField(title="手机号码",sort=10,align=1)
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public String getOptCode() {
		return optCode;
	}

	public void setOptCode(String optCode) {
		this.optCode = optCode;
	}

	public String getAmountYuan() {
		return amountYuan;
	}

	public void setAmountYuan(String amountYuan) {
		this.amountYuan = amountYuan;
	}

	public String getPayMethodName() {
		return payMethodName;
	}

	public void setPayMethodName(String payMethodName) {
		this.payMethodName = payMethodName;
	}

	public Integer getAcctItemTypeId() {
		return acctItemTypeId;
	}

	public void setAcctItemTypeId(Integer acctItemTypeId) {
		this.acctItemTypeId = acctItemTypeId;
	}

	public String getPaymentOrderId() {
		return paymentOrderId;
	}

	public void setPaymentOrderId(String paymentOrderId) {
		this.paymentOrderId = paymentOrderId;
	}

	public String getPhoto() {
    	return photo;
    }
    
    public void setPhoto(String photo) {
    	this.photo = photo;
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

    public String getAccountBalanceId() {
        return accountBalanceId;
    }

    public void setAccountBalanceId(String accountBalanceId) {
        this.accountBalanceId = accountBalanceId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getExternalSeq() {
        return externalSeq;
    }

    public void setExternalSeq(String externalSeq) {
        this.externalSeq = externalSeq == null ? null : externalSeq.trim();
    }

    public String getPriPayLogId() {
        return priPayLogId;
    }

    public void setPriPayLogId(String priPayLogId) {
        this.priPayLogId = priPayLogId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId == null ? null : bankId.trim();
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod == null ? null : payMethod.trim();
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType == null ? null : certificateType.trim();
    }

    public String getCertificateCode() {
        return certificateCode;
    }

    public void setCertificateCode(String certificateCode) {
        this.certificateCode = certificateCode == null ? null : certificateCode.trim();
    }

    public Double getOweFee() {
        return oweFee;
    }

    public void setOweFee(Double oweFee) {
        this.oweFee = oweFee;
    }

    @ExcelField(title="金额(元)",sort=20,align=1)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
    
    public String getEffectDateStr() {
    	return effectDateStr;
    }
    
    public void setEffectDateStr(String effectDateStr) {
    	this.effectDateStr = effectDateStr;
    }

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getExtendSpecPaymentId() {
		return extendSpecPaymentId;
	}

	public void setExtendSpecPaymentId(String extendSpecPaymentId) {
		this.extendSpecPaymentId = extendSpecPaymentId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getExtendAmount() {
		return extendAmount;
	}

	public void setExtendAmount(String extendAmount) {
		this.extendAmount = extendAmount;
	}
	
}