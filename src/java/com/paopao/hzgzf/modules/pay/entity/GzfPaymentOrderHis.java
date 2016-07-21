/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.pay.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 外围缴费工单历史表Entity
 * @author songyahe
 * @version 2016-04-25
 */
public class GzfPaymentOrderHis extends DataEntity<GzfPaymentOrderHis> {
	
	private static final long serialVersionUID = 1L;
	private String accountBalanceId;		// 为每个余额帐本生成的唯一编号，只具有逻辑上的含义，没有物理意义。
	private String accountId;		// account_id
	private String phoneNo;		// phone_no
	private String custId;		// cust_id
	private String optCode;		// 1 : 缴费    2 : 冲正     3 : 退费
	private String externalSeq;		// 存放订单号
	private String priPayLogId;		// 反销时，需要增加一条缴费记录，
	private String bankId;		// bank_id
	private String paymentType;		// 0按帐户缴费；            1按用户结清费用；
	private String payMethod;		// 1  前台收现金            2  银行代收
	private String certificateType;		// 根据凭证类型记录不同的信息。            凭证类型：            1-银行缴费凭证编号
	private String certificateCode;		// 根据凭证类型记录不同的信息
	private Double oweFee;		// owe_fee
	private Double amount;		// 缴款的总费用
	private Integer balanceTypeId;		// balance_type_id
	private Integer spePaymentId;		// spe_payment_id
	private Date effectDate;		// effect_date
	private Date expireDate;		// expire_date
	private String state;		// 0:缴费 1:冲正
	private Date payDate;		// pay_date
	private String photo;		// photo
	private String transferId;		// transfer_id
	private String channelType;		// channel_type
	private String processStatus;		// process_status
	private Date processDate;		// process_date
	private String errorMsg;		// error_msg
	
	public GzfPaymentOrderHis() {
		super();
	}

	public GzfPaymentOrderHis(String id){
		super(id);
	}

	@Length(min=0, max=64, message="为每个余额帐本生成的唯一编号，只具有逻辑上的含义，没有物理意义。长度必须介于 0 和 64 之间")
	public String getAccountBalanceId() {
		return accountBalanceId;
	}

	public void setAccountBalanceId(String accountBalanceId) {
		this.accountBalanceId = accountBalanceId;
	}
	
	@Length(min=0, max=64, message="account_id长度必须介于 0 和 64 之间")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Length(min=0, max=20, message="phone_no长度必须介于 0 和 20 之间")
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	@Length(min=0, max=64, message="cust_id长度必须介于 0 和 64 之间")
	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}
	
	@Length(min=0, max=32, message="1 : 缴费    2 : 冲正     3 : 退费长度必须介于 0 和 32 之间")
	public String getOptCode() {
		return optCode;
	}

	public void setOptCode(String optCode) {
		this.optCode = optCode;
	}
	
	@Length(min=0, max=40, message="存放订单号长度必须介于 0 和 40 之间")
	public String getExternalSeq() {
		return externalSeq;
	}

	public void setExternalSeq(String externalSeq) {
		this.externalSeq = externalSeq;
	}
	
	@Length(min=0, max=64, message="反销时，需要增加一条缴费记录，长度必须介于 0 和 64 之间")
	public String getPriPayLogId() {
		return priPayLogId;
	}

	public void setPriPayLogId(String priPayLogId) {
		this.priPayLogId = priPayLogId;
	}
	
	@Length(min=0, max=64, message="bank_id长度必须介于 0 和 64 之间")
	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	
	@Length(min=0, max=1, message="0按帐户缴费；            1按用户结清费用；长度必须介于 0 和 1 之间")
	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	@Length(min=0, max=1, message="1  前台收现金            2  银行代收长度必须介于 0 和 1 之间")
	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	
	@Length(min=0, max=1, message="根据凭证类型记录不同的信息。            凭证类型：            1-银行缴费凭证编号长度必须介于 0 和 1 之间")
	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	
	@Length(min=0, max=64, message="根据凭证类型记录不同的信息长度必须介于 0 和 64 之间")
	public String getCertificateCode() {
		return certificateCode;
	}

	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}
	
	public Double getOweFee() {
		return oweFee;
	}

	public void setOweFee(Double oweFee) {
		this.oweFee = oweFee;
	}
	
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	@Length(min=0, max=1, message="0:缴费 1:冲正长度必须介于 0 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	
	@Length(min=0, max=255, message="photo长度必须介于 0 和 255 之间")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Length(min=0, max=64, message="transfer_id长度必须介于 0 和 64 之间")
	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}
	
	@Length(min=0, max=16, message="channel_type长度必须介于 0 和 16 之间")
	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	
	@Length(min=0, max=1, message="process_status长度必须介于 0 和 1 之间")
	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}
	
	@Length(min=0, max=4000, message="error_msg长度必须介于 0 和 4000 之间")
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}