package com.paopao.hzgzf.modules.pay.entity;

import java.util.Date;

import com.paopao.hzgzf.common.persistence.DataEntity;
/**
 * 余额类型，用于定义余额是否可提取，是否可销账，是否提供发票
 * @author songyh
 *
 */
public class GzfBalanceType extends DataEntity<GzfBalanceType>{
	private static final long serialVersionUID = 1L;

	private Integer balanceTypeId;

    private String balanceTypeName;

    private String allowDraw;//是否可提取 1-是 0-否

    private String allowWriteoff;//是否可销帐 1-是 0－否

    private String invoiceOffer;//是否提供发票 1-是 0－否

    private Integer pri;//销账优先级

    private Date stateDate;
    
    public GzfBalanceType(){
    	super();
    }
    
    public GzfBalanceType(String id){
    	super(id);
    }

    public Integer getBalanceTypeId() {
        return balanceTypeId;
    }

    public void setBalanceTypeId(Integer balanceTypeId) {
        this.balanceTypeId = balanceTypeId;
    }

    public String getBalanceTypeName() {
        return balanceTypeName;
    }

    public void setBalanceTypeName(String balanceTypeName) {
        this.balanceTypeName = balanceTypeName == null ? null : balanceTypeName.trim();
    }

    public String getAllowDraw() {
        return allowDraw;
    }

    public void setAllowDraw(String allowDraw) {
        this.allowDraw = allowDraw == null ? null : allowDraw.trim();
    }

    public String getAllowWriteoff() {
        return allowWriteoff;
    }

    public void setAllowWriteoff(String allowWriteoff) {
        this.allowWriteoff = allowWriteoff == null ? null : allowWriteoff.trim();
    }

    public String getInvoiceOffer() {
        return invoiceOffer;
    }

    public void setInvoiceOffer(String invoiceOffer) {
        this.invoiceOffer = invoiceOffer == null ? null : invoiceOffer.trim();
    }

    public Integer getPri() {
        return pri;
    }

    public void setPri(Integer pri) {
        this.pri = pri;
    }

    public Date getStateDate() {
        return stateDate;
    }

    public void setStateDate(Date stateDate) {
        this.stateDate = stateDate;
    }
}