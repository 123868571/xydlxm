/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.entity;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 通用变更记录Entity
 * @author zdk
 * @version 2016-07-14
 */
public class ComRecord extends DataEntity<ComRecord> {
	
	/** 非附件修改 */
	public static final String NOT_ATTACH = "0";
	/** 附件修改 */
	public static final String ATTACH = "1";
	
	/** 正常修改 */
	public static final String MOD_NOR = "0";
	/** 附件添加 */
	public static final String MOD_ATTACH_ADD = "1";
	/** 附件删除 */
	public static final String MOD_ATTACH_DEL = "2";
	
	private static final long serialVersionUID = 1L;
	private String referId;		// 关联的业务ID
	private String referType;		// 业务类型
	private String changeColumn;	// 修改的字段名
	private String changeColumnDesc;	// 修改的字段描述
	private String befValue;		// 修改前内容
	private String aftValue;		// 修改后内容
	private String isAttach;		// 是否是附件
	private String modifyType;		// 修改类型
	private String opId;		// op_id
	private String remark;		// remark
	
	public ComRecord() {
		super();
	}

	public ComRecord(String id){
		super(id);
	}

	@Length(min=0, max=64, message="关联的业务ID长度必须介于 0 和 64 之间")
	public String getReferId() {
		return referId;
	}

	public void setReferId(String referId) {
		this.referId = referId;
	}
	
	@Length(min=0, max=64, message="业务类型长度必须介于 0 和 64 之间")
	public String getReferType() {
		return referType;
	}

	public void setReferType(String referType) {
		this.referType = referType;
	}
	
	@Length(min=0, max=200, message="修改的字段名长度必须介于 0 和 200 之间")
	public String getChangeColumn() {
		return changeColumn;
	}

	public void setChangeColumn(String changeColumn) {
		this.changeColumn = changeColumn;
	}
	
	@Length(min=0, max=500, message="修改前内容长度必须介于 0 和 500 之间")
	public String getBefValue() {
		return befValue;
	}

	public void setBefValue(String befValue) {
		this.befValue = befValue;
	}
	
	@Length(min=0, max=500, message="修改后内容长度必须介于 0 和 500 之间")
	public String getAftValue() {
		return aftValue;
	}

	public void setAftValue(String aftValue) {
		this.aftValue = aftValue;
	}
	
	@Length(min=0, max=1, message="是否是附件长度必须介于 0 和 1 之间")
	public String getIsAttach() {
		return isAttach;
	}

	public void setIsAttach(String isAttach) {
		this.isAttach = isAttach;
	}
	
	@Length(min=0, max=1, message="修改类型长度必须介于 0 和 1 之间")
	public String getModifyType() {
		return modifyType;
	}

	public void setModifyType(String modifyType) {
		this.modifyType = modifyType;
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

	@Length(min=0, max=300, message="changeColumnDesc长度必须介于 0 和 300 之间")
	public String getChangeColumnDesc() {
		return changeColumnDesc;
	}

	public void setChangeColumnDesc(String changeColumnDesc) {
		this.changeColumnDesc = changeColumnDesc;
	}
	
}