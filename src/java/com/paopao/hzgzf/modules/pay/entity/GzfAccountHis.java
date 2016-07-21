/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.pay.entity;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 历史帐户Entity
 * @author songyahe
 * @version 2016-03-30
 */
public class GzfAccountHis extends DataEntity<GzfAccountHis> {
	
	private static final long serialVersionUID = 1L;
	private String accountId;		// account_id
	private String accountName;		// account_name
	private String custId;		// cust_id
	private String accountType;		// 1：基本帐户            2：合户
	private String payMethod;		// 0：自付；            1：托收            2：代扣；
	private String state;		// 0：正常；            1：已注销，余额未转移；            2：已注销，余额已结清；
	private Integer accountCredit;		// account_credit
	private String phoneNo;		// phone_no
	private String cardid;	//身份证号
	private Integer opId;		// op_id
	private Integer cycleEndDay;		// cycle_end_day
	
	public GzfAccountHis() {
		super();
	}

	public GzfAccountHis(String id){
		super(id);
	}

	@Length(min=0, max=64, message="account_id长度必须介于 0 和 64 之间")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Length(min=0, max=100, message="account_name长度必须介于 0 和 100 之间")
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	@Length(min=0, max=64, message="cust_id长度必须介于 0 和 64 之间")
	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}
	
	@Length(min=0, max=1, message="1：基本帐户            2：合户长度必须介于 0 和 1 之间")
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	@Length(min=0, max=1, message="0：自付；            1：托收            2：代扣；长度必须介于 0 和 1 之间")
	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	
	@Length(min=0, max=1, message="0：正常；            1：已注销，余额未转移；            2：已注销，余额已结清；长度必须介于 0 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public Integer getAccountCredit() {
		return accountCredit;
	}

	public void setAccountCredit(Integer accountCredit) {
		this.accountCredit = accountCredit;
	}
	
	@Length(min=0, max=20, message="phone_no长度必须介于 0 和 20 之间")
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public Integer getOpId() {
		return opId;
	}

	public void setOpId(Integer opId) {
		this.opId = opId;
	}
	
	public Integer getCycleEndDay() {
		return cycleEndDay;
	}

	public void setCycleEndDay(Integer cycleEndDay) {
		this.cycleEndDay = cycleEndDay;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	
}