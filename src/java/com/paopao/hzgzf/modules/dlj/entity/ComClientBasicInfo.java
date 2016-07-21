/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.entity;

import com.paopao.hzgzf.modules.sys.entity.Area;
import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 客户资料管理Entity
 * @author zdk
 * @version 2016-07-14
 */
public class ComClientBasicInfo extends DataEntity<ComClientBasicInfo> {
	
	public static final String REFER_TYPE_CLIENT = "clientInfo";
	
	private static final long serialVersionUID = 1L;
	private Area area;		// 地区
	private String clientName;		// 客户姓名
	private String archiveNo;		// 客户档案编号
	private String cardId;		// 身份证
	private String address;		// 用电地址
	private String estateLicense;		// 房产证编号
	private String issuingAuthority;		// 发证机关
	private String operatorName;		// 经办人
	private String operatorCardId;		// 经办人身份证号
	private String opId;		// op_id
	private String remark;		// remark
	
	public ComClientBasicInfo() {
		super();
	}

	public ComClientBasicInfo(String id){
		super(id);
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@Length(min=0, max=255, message="客户姓名长度必须介于 0 和 255 之间")
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	@Length(min=0, max=200, message="客户档案编号长度必须介于 0 和 200 之间")
	public String getArchiveNo() {
		return archiveNo;
	}

	public void setArchiveNo(String archiveNo) {
		this.archiveNo = archiveNo;
	}
	
	@Length(min=0, max=20, message="身份证长度必须介于 0 和 20 之间")
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	@Length(min=0, max=500, message="用电地址长度必须介于 0 和 500 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=200, message="房产证编号长度必须介于 0 和 200 之间")
	public String getEstateLicense() {
		return estateLicense;
	}

	public void setEstateLicense(String estateLicense) {
		this.estateLicense = estateLicense;
	}
	
	@Length(min=0, max=500, message="发证机关长度必须介于 0 和 500 之间")
	public String getIssuingAuthority() {
		return issuingAuthority;
	}

	public void setIssuingAuthority(String issuingAuthority) {
		this.issuingAuthority = issuingAuthority;
	}
	
	@Length(min=0, max=200, message="经办人长度必须介于 0 和 200 之间")
	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	@Length(min=0, max=20, message="经办人身份证号长度必须介于 0 和 20 之间")
	public String getOperatorCardId() {
		return operatorCardId;
	}

	public void setOperatorCardId(String operatorCardId) {
		this.operatorCardId = operatorCardId;
	}
	
	@Length(min=0, max=11, message="op_id长度必须介于 0 和 11 之间")
	public String getOpId() {
		return opId;
	}

	public void setOpId(String opId) {
		this.opId = opId;
	}
	
	@Length(min=0, max=255, message="remark长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}