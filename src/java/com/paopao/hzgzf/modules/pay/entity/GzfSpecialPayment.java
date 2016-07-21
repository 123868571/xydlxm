package com.paopao.hzgzf.modules.pay.entity;

import java.util.Date;

import com.paopao.hzgzf.common.persistence.DataEntity;
/**
 * 专款专用,如水费、电费、房租费
 * @author songyh
 *
 */
public class GzfSpecialPayment extends DataEntity<GzfSpecialPayment>{
	private static final long serialVersionUID = 1L;

	private Integer spePaymentId;

    private String spePaymentDesc;

    private Integer acctItemTypeId;//可为其销账的帐目

    private Date stateDate;
    
    public GzfSpecialPayment(){
    	super();
    }
    
    public GzfSpecialPayment(Integer spePaymentId){
    	super(spePaymentId.toString());
    }

    public Integer getSpePaymentId() {
        return spePaymentId;
    }

    public void setSpePaymentId(Integer spePaymentId) {
        this.spePaymentId = spePaymentId;
    }

    public String getSpePaymentDesc() {
        return spePaymentDesc;
    }

    public void setSpePaymentDesc(String spePaymentDesc) {
        this.spePaymentDesc = spePaymentDesc == null ? null : spePaymentDesc.trim();
    }

    public Integer getAcctItemTypeId() {
        return acctItemTypeId;
    }

    public void setAcctItemTypeId(Integer acctItemTypeId) {
        this.acctItemTypeId = acctItemTypeId;
    }

    public Date getStateDate() {
        return stateDate;
    }

    public void setStateDate(Date stateDate) {
        this.stateDate = stateDate;
    }
}