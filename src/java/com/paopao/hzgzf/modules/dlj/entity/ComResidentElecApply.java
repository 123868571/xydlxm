/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.entity;

import com.paopao.hzgzf.modules.dlj.entity.interfaces.IProcessObject;
import com.paopao.hzgzf.modules.sys.entity.Area;
import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;
import com.paopao.hzgzf.common.process.util.ProcConst;

/**
 * 城乡居民生活用电需求表Entity
 * @author zhoudk
 * @version 2016-06-23
 */
public class ComResidentElecApply extends DataEntity<ComResidentElecApply> implements IProcessObject {
	
	private static final long serialVersionUID = 1L;
	private Area area;		// 地区
	private String serial;		// 申请编号
	private String custNo;		// 客户编号
	private String name;		// 客户姓名
	private String cardId;		// 身份证
	private String address;		// 用电地址
	private String estateLicense;		// 房产证编号
	private String issuingAuthority;		// 发证机关
	private String operatorName;		// 经办人
	private String operatorCardId;		// 经办人身份证号
	private int elecType;		// 供电及计费方式
	private String capacity;		// 容量
	private int payType;		// 电费支付方式
	private int payWraping;		// 电费尾款归整
	private String linkName;		// 联系人
	private String linkTel;		// 联系电话
	private int checkStatus;		// check_status
	private String checkContent;		// check_content
	private int opId;		// op_id
	private String remark;		// remark
	
	public ComResidentElecApply() {
		super();
		checkStatus = ProcConst.EDIT; // 新生成的申请，应该是待提交状态
		checkContent = "";
	}

	public ComResidentElecApply(String id){
		super(id);
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@Length(min=0, max=32, message="申请编号长度必须介于 0 和 32 之间")
	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	@Length(min=0, max=32, message="客户编号长度必须介于 0 和 200 之间")
	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	
	@Length(min=0, max=200, message="客户姓名长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
//	@Length(min=0, max=2, message="供电及计费方式长度必须介于 0 和 2 之间")
	public int getElecType() {
		return elecType;
	}

	public void setElecType(int elecType) {
		this.elecType = elecType;
	}
	
	@Length(min=0, max=50, message="容量长度必须介于 0 和 50 之间")
	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	
//	@Length(min=0, max=2, message="电费支付方式长度必须介于 0 和 2 之间")
	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}
	
//	@Length(min=0, max=5, message="电费尾款归整长度必须介于 0 和 5 之间")
	public int getPayWraping() {
		return payWraping;
	}

	public void setPayWraping(int payWraping) {
		this.payWraping = payWraping;
	}
	
	@Length(min=0, max=200, message="联系人长度必须介于 0 和 200 之间")
	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	
	@Length(min=0, max=50, message="联系电话长度必须介于 0 和 50 之间")
	public String getLinkTel() {
		return linkTel;
	}

	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}
	
//	@Length(min=0, max=10, message="check_status长度必须介于 0 和 10 之间")
	public int getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(int checkStatus) {
		this.checkStatus = checkStatus;
	}
	
	@Length(min=0, max=500, message="check_content长度必须介于 0 和 500 之间")
	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}
	
	public int getOpId() {
		return opId;
	}

	public void setOpId(int opId) {
		this.opId = opId;
	}
	
	@Length(min=0, max=255, message="remark长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public void checkPass() {
		this.checkStatus = ProcConst.FINISH;
	}

	@Override
	public void checkReject() {
		this.checkStatus = ProcConst.REJECT;
	}
	
}