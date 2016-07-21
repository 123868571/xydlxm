package com.paopao.hzgzf.modules.pay.entity;

import java.util.List;

import com.paopao.hzgzf.common.persistence.DataEntity;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;

public class AccountBank extends DataEntity<AccountBank>{
	private static final long serialVersionUID = 1L;

	private String accountName;

    private String custId;

    private String accountType;

    private String payMethod;

    private String state;

    private Integer accountCredit;

    private String phoneNo;

    private Integer opId;

    private String remarks;

    private Integer cycleEndDay;
    
    private String acctItemType;
    
    private String cardNo;

	private String bankId;
    
    private String bankName;
    
	private GzfHousePerson housePerson;//住户信息

	private List<String> acctItemTypeList;//用于前台复选框的选中显示
	
	public AccountBank(){
		super();
	}
	
	public AccountBank(String id){
		super(id);
	}
    
	public List<String> getAcctItemTypeList() {
		return acctItemTypeList;
	}
	
	public void setAcctItemTypeList(List<String> acctItemTypeList) {
		this.acctItemTypeList = acctItemTypeList;
	}
	
    public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getCycleEndDay() {
		return cycleEndDay;
	}

	public void setCycleEndDay(Integer cycleEndDay) {
		this.cycleEndDay = cycleEndDay;
	}

	public String getAcctItemType() {
		return acctItemType;
	}

	public void setAcctItemType(String acctItemType) {
		this.acctItemType = acctItemType;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public GzfHousePerson getHousePerson() {
		return housePerson;
	}

	public void setHousePerson(GzfHousePerson housePerson) {
		this.housePerson = housePerson;
	}
}
