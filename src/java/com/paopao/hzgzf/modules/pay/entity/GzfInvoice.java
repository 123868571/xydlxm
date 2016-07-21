package com.paopao.hzgzf.modules.pay.entity;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 发票
 * @author songyh
 *
 */
public class GzfInvoice extends DataEntity<GzfInvoice>{
	private static final long serialVersionUID = 1L;

	private String paymentId;//缴费流水

    private String accountId;

    private String accountName;

    private Integer invoiceAmount;//发票金额

    private Integer printCount;//打印次数

    private Integer supplyCount;//补打次数

    private String invoiceType;//发票类型

    private String invoiceCode;//发票号码

    private Integer invoiceBatchId;//发票批次号

    private String payAddress;//缴费地点

    private String custBankName;//银行名称

    private String custBankAccountCode;//银行帐户

    private String custBankAccountName;//银行帐户名称

    private String agreementCode;//托收协议号

    public GzfInvoice(){
    	super();
    }
    
    public GzfInvoice(String id){
    	super(id);
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public Integer getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Integer invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Integer getPrintCount() {
        return printCount;
    }

    public void setPrintCount(Integer printCount) {
        this.printCount = printCount;
    }

    public Integer getSupplyCount() {
        return supplyCount;
    }

    public void setSupplyCount(Integer supplyCount) {
        this.supplyCount = supplyCount;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType == null ? null : invoiceType.trim();
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode == null ? null : invoiceCode.trim();
    }

    public Integer getInvoiceBatchId() {
        return invoiceBatchId;
    }

    public void setInvoiceBatchId(Integer invoiceBatchId) {
        this.invoiceBatchId = invoiceBatchId;
    }

    public String getPayAddress() {
        return payAddress;
    }

    public void setPayAddress(String payAddress) {
        this.payAddress = payAddress == null ? null : payAddress.trim();
    }

    public String getCustBankName() {
        return custBankName;
    }

    public void setCustBankName(String custBankName) {
        this.custBankName = custBankName == null ? null : custBankName.trim();
    }

    public String getCustBankAccountCode() {
        return custBankAccountCode;
    }

    public void setCustBankAccountCode(String custBankAccountCode) {
        this.custBankAccountCode = custBankAccountCode == null ? null : custBankAccountCode.trim();
    }

    public String getCustBankAccountName() {
        return custBankAccountName;
    }

    public void setCustBankAccountName(String custBankAccountName) {
        this.custBankAccountName = custBankAccountName == null ? null : custBankAccountName.trim();
    }

    public String getAgreementCode() {
        return agreementCode;
    }

    public void setAgreementCode(String agreementCode) {
        this.agreementCode = agreementCode == null ? null : agreementCode.trim();
    }
}