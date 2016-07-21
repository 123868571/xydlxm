package com.paopao.hzgzf.modules.pay.entity;

import java.util.Date;

import com.paopao.hzgzf.common.persistence.DataEntity;
/**
 * 绑定银行卡entity
 * @author songyh
 *
 */
public class GzfBankCard extends DataEntity<GzfBankCard>{

	private static final long serialVersionUID = 1L;

	private String accountId;

    private String bankId;

    private String bankName;

    private String cardNo;

    private Integer acctItemTypeId;//账目类型
    
    private GzfAccount gzfAccount;

    public GzfAccount getGzfAccount() {
		return gzfAccount;
	}

	public void setGzfAccount(GzfAccount gzfAccount) {
		this.gzfAccount = gzfAccount;
	}

	public GzfBankCard(){
    	super();
    }

    public GzfBankCard(String id){
    	super(id);
    }
    
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId == null ? null : bankId.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public Integer getAcctItemTypeId() {
        return acctItemTypeId;
    }

    public void setAcctItemTypeId(Integer acctItemTypeId) {
        this.acctItemTypeId = acctItemTypeId;
    }
}