/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.process.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 流程实例管理Entity
 * @author zhoudk
 * @version 2016-06-23
 */
public class SysProcessInstance extends DataEntity<SysProcessInstance> {
	
	private static final long serialVersionUID = 1L;
	private String processType;		// 流程类型，预设字段
	private String applyId;		// 业务申请的ID（主要是可以通过这个字段找到历史流程实例）
	private Date startTime;		// 起始时间
	private Date endTime;		// 结束时间
	private int status;		// 流程实例状态
	private String remark;		// remark
	
	public SysProcessInstance() {
		super();
	}

	public SysProcessInstance(String id){
		super(id);
	}

	@Length(min=0, max=100, message="流程类型，预设字段长度必须介于 0 和 100 之间")
	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}
	
	@Length(min=0, max=64, message="业务申请的ID（主要是可以通过这个字段找到历史流程实例）长度必须介于 0 和 64 之间")
	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=5, message="流程实例状态长度必须介于 0 和 5 之间")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@Length(min=0, max=255, message="remark长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}