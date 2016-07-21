/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.process.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 流程实例明细Entity
 * @author zhoudk
 * @version 2016-06-23
 */
public class SysProcessInstanceDetail extends DataEntity<SysProcessInstanceDetail> {
	
	private static final long serialVersionUID = 1L;
	private String instanceId;		// 流程实例ID
	private String stepId;		// 步骤
	private String stepName;	// 步骤名称
	private String rollId;		// 处理人角色
	private String operatorId;		// 处理人
	private Date opDate;		// 处理日期
	private String opinion;		// 处理意见
	private int status;		// 处理状态
	
	public SysProcessInstanceDetail() {
		super();
	}

	public SysProcessInstanceDetail(String id){
		super(id);
	}

	@Length(min=0, max=64, message="流程实例ID长度必须介于 0 和 64 之间")
	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	
	@Length(min=0, max=100, message="步骤长度必须介于 0 和 100 之间")
	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	
	@Length(min=0, max=1000, message="处理人角色长度必须介于 0 和 1000 之间")
	public String getRollId() {
		return rollId;
	}

	public void setRollId(String rollId) {
		this.rollId = rollId;
	}
	
	@Length(min=0, max=64, message="处理人长度必须介于 0 和 64 之间")
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOpDate() {
		return opDate;
	}

	public void setOpDate(Date opDate) {
		this.opDate = opDate;
	}
	
	@Length(min=0, max=255, message="处理意见长度必须介于 0 和 255 之间")
	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
	@Length(min=0, max=5, message="处理状态长度必须介于 0 和 5 之间")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Length(min=0, max=255, message="处理状态长度必须介于 0 和255 之间")
	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	
	
}