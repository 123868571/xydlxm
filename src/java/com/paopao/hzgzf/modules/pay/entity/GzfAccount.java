package com.paopao.hzgzf.modules.pay.entity;

import java.util.Date;
import java.util.List;

import com.paopao.hzgzf.common.persistence.DataEntity;
import com.paopao.hzgzf.common.utils.excel.annotation.ExcelField;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;

import javax.validation.constraints.NotNull;

/**
 * 帐户entity
 *
 * @author songyh
 */
public class GzfAccount extends DataEntity<GzfAccount> {
    private static final long serialVersionUID = 1L;

    private String accountName;

    private String custId;

    private String accountType;

    private String payMethod;

    private String state;

    private Integer accountCredit;

    private String phoneNo;
    
    private String cardid;

    private Integer opId;

    private String remarks;

    private Integer cycleEndDay;

    private GzfHousePerson housePerson;//住户信息

    private Double fee;//用于存放计算出的钱

    private List<GzfPayment> payments;//缴费明细

    private List<GzfAccountBalance> balances;//帐本

    public List<GzfPayment> getPayments() {
        return payments;
    }

    public void setPayments(List<GzfPayment> payments) {
        this.payments = payments;
    }

    public List<GzfAccountBalance> getBalances() {
        return balances;
    }

    public void setBalances(List<GzfAccountBalance> balances) {
        this.balances = balances;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public GzfAccount() {
        super();
    }

    public GzfAccount(String id) {
        super(id);
    }

    @NotNull(message = "姓名不能为空")
    @ExcelField(title = "姓名")
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId == null ? null : custId.trim();
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod == null ? null : payMethod.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getAccountCredit() {
        return accountCredit;
    }

    public void setAccountCredit(Integer accountCredit) {
        this.accountCredit = accountCredit;
    }

    @NotNull(message = "手机号不能为空")
    @ExcelField(title = "手机号")
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
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
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getCycleEndDay() {
        return cycleEndDay;
    }

    public void setCycleEndDay(Integer cycleEndDay) {
        this.cycleEndDay = cycleEndDay;
    }

    public GzfHousePerson getHousePerson() {
        return housePerson;
    }

    public void setHousePerson(GzfHousePerson housePerson) {
        this.housePerson = housePerson;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    private Date startDate;

    private Date endDate;


    public Integer getOverDay() {
        return overDay;
    }

    public void setOverDay(Integer overDay) {
        this.overDay = overDay;
    }

    public boolean isOweMoney() {
        return oweMoney;
    }

    public void setOweMoney(boolean oweMoney) {
        this.oweMoney = oweMoney;
    }

    private boolean oweMoney;

    private Integer overDay;

    public List<String> getAccountIdList() {
        return accountIdList;
    }

    public void setAccountIdList(List<String> accountIdList) {
        this.accountIdList = accountIdList;
    }

    private List<String> accountIdList;

    public List<String> getPalacesIdList() {
        return palacesIdList;
    }

    public void setPalacesIdList(List<String> palacesIdList) {
        this.palacesIdList = palacesIdList;
    }

    private List<String> palacesIdList;


    @ExcelField(title = "房屋地址")
    public String getAddress() {
        //${gzfAccount.housePerson.gzfHouseInfo.gzfPalaces.gzfVillage.name} ${gzfAccount.housePerson.gzfHouseInfo.gzfPalaces.name}苑 ${gzfAccount.housePerson.gzfHouseInfo.buildNum}楼 ${gzfAccount.housePerson.gzfHouseInfo.unit}单元 ${gzfAccount.housePerson.gzfHouseInfo.room}号
        return this.housePerson.getGzfHouseInfo().getGzfPalaces().getGzfVillage().getName() + " "
          + this.housePerson.getGzfHouseInfo().getGzfPalaces().getName() + " " + this.housePerson
          .getGzfHouseInfo().getUnit() + " " + this.housePerson.getGzfHouseInfo().getRoom()
          ;
    }

    @ExcelField(title = "是否欠费")
    public String getIsOweMoney() {
        return oweMoney ? "已欠费" + this.getOverDay() + "天" : "正常";
    }

    @ExcelField(title = "欠费金额（元）")
    public String getOwnFee() {
        return this.fee == null ? "0.00" : (this.fee/100) + "";
    }

    @ExcelField(title = "到期时间")
    public Date getUpdateDate() {
        return this.updateDate;
    }

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
}
