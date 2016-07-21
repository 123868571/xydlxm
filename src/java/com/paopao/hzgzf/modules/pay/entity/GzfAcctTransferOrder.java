/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.pay.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 转帐工单Entity
 * @author songyahe
 * @version 2016-04-25
 */
public class GzfAcctTransferOrder extends DataEntity<GzfAcctTransferOrder> {
	
	private static final long serialVersionUID = 1L;
	private String srcAcctId;		// src_acct_id
	private String destAcctId;		// dest_acct_id
	private Integer srcSpecPaymentId;		// src_spec_payment_id
	private Integer destSpecPaymentId;		// dest_spec_payment_id
	private String optCode;		// opt_code
	private String srcBalanceId;		// src_balance_id
	private String destBalanceId;		// dest_balance_id
	private Double amount;		// amount
	private String errorMsg;		// error_msg
	private String processStatus;		// process_status
	
	public GzfAcctTransferOrder() {
		super();
	}

	public GzfAcctTransferOrder(String id){
		super(id);
	}

	@Length(min=1, max=64, message="src_acct_id长度必须介于 1 和 64 之间")
	public String getSrcAcctId() {
		return srcAcctId;
	}

	public void setSrcAcctId(String srcAcctId) {
		this.srcAcctId = srcAcctId;
	}
	
	@Length(min=1, max=64, message="dest_acct_id长度必须介于 1 和 64 之间")
	public String getDestAcctId() {
		return destAcctId;
	}

	public void setDestAcctId(String destAcctId) {
		this.destAcctId = destAcctId;
	}
	
	@NotNull(message="src_spec_payment_id不能为空")
	public Integer getSrcSpecPaymentId() {
		return srcSpecPaymentId;
	}

	public void setSrcSpecPaymentId(Integer srcSpecPaymentId) {
		this.srcSpecPaymentId = srcSpecPaymentId;
	}
	
	@NotNull(message="dest_spec_payment_id不能为空")
	public Integer getDestSpecPaymentId() {
		return destSpecPaymentId;
	}

	public void setDestSpecPaymentId(Integer destSpecPaymentId) {
		this.destSpecPaymentId = destSpecPaymentId;
	}
	
	@Length(min=0, max=32, message="opt_code长度必须介于 0 和 32 之间")
	public String getOptCode() {
		return optCode;
	}

	public void setOptCode(String optCode) {
		this.optCode = optCode;
	}
	
	@Length(min=0, max=64, message="src_balance_id长度必须介于 0 和 64 之间")
	public String getSrcBalanceId() {
		return srcBalanceId;
	}

	public void setSrcBalanceId(String srcBalanceId) {
		this.srcBalanceId = srcBalanceId;
	}
	
	@Length(min=0, max=64, message="dest_balance_id长度必须介于 0 和 64 之间")
	public String getDestBalanceId() {
		return destBalanceId;
	}

	public void setDestBalanceId(String destBalanceId) {
		this.destBalanceId = destBalanceId;
	}
	
	@NotNull(message="amount不能为空")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@Length(min=0, max=4000, message="error_msg长度必须介于 0 和 4000 之间")
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	@Length(min=0, max=1, message="process_status长度必须介于 0 和 1 之间")
	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	
}