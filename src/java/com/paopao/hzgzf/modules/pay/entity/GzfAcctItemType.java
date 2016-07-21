package com.paopao.hzgzf.modules.pay.entity;

import java.util.Date;

import com.paopao.hzgzf.common.persistence.DataEntity;
/**
 * 帐目类型entiry,定义帐目类型，如电费、水费、物业费、房费
 * @author songyh
 *
 */
public class GzfAcctItemType extends DataEntity<GzfAcctItemType>{
	private static final long serialVersionUID = 1L;

	private Integer acctItemTypeId;

    private String acctItemTypeCode;

    private String acctItemTypeName;

    private String acctItemTypeDesc;

    private Integer printPriority;//打印优先级

    private Integer billPriority;//销账优先级

    private Date stateDate;

    public GzfAcctItemType(){
    	super();
    }
    
    public GzfAcctItemType(String id){
    	super(id);
    }
    
    public Integer getAcctItemTypeId() {
        return acctItemTypeId;
    }

    public void setAcctItemTypeId(Integer acctItemTypeId) {
        this.acctItemTypeId = acctItemTypeId;
    }

    public String getAcctItemTypeCode() {
        return acctItemTypeCode;
    }

    public void setAcctItemTypeCode(String acctItemTypeCode) {
        this.acctItemTypeCode = acctItemTypeCode == null ? null : acctItemTypeCode.trim();
    }

    public String getAcctItemTypeName() {
        return acctItemTypeName;
    }

    public void setAcctItemTypeName(String acctItemTypeName) {
        this.acctItemTypeName = acctItemTypeName == null ? null : acctItemTypeName.trim();
    }

    public String getAcctItemTypeDesc() {
        return acctItemTypeDesc;
    }

    public void setAcctItemTypeDesc(String acctItemTypeDesc) {
        this.acctItemTypeDesc = acctItemTypeDesc == null ? null : acctItemTypeDesc.trim();
    }

    public Integer getPrintPriority() {
        return printPriority;
    }

    public void setPrintPriority(Integer printPriority) {
        this.printPriority = printPriority;
    }

    public Integer getBillPriority() {
        return billPriority;
    }

    public void setBillPriority(Integer billPriority) {
        this.billPriority = billPriority;
    }

    public Date getStateDate() {
        return stateDate;
    }

    public void setStateDate(Date stateDate) {
        this.stateDate = stateDate;
    }
}