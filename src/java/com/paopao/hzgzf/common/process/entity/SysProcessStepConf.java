/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.process.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 流程步骤分配Entity
 * @author zhoudk
 * @version 2016-06-22
 */
public class SysProcessStepConf extends DataEntity<SysProcessStepConf> {
	
	private static final long serialVersionUID = 1L;
	private String processType;		// 流程类型
	private String step;		// 步骤
	private String stepName;		// 步骤名称
	private String rollId;		// 处理人的角色
	private String remark;		// 备注
	private Date beginCreateDate;		// 开始 创建日期
	private Date endCreateDate;		// 结束 创建日期
	
	public SysProcessStepConf() {
		super();
	}

	public SysProcessStepConf(String id){
		super(id);
	}

	@Length(min=0, max=100, message="流程类型长度必须介于 0 和 100 之间")
	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}
	
	@Length(min=0, max=50, message="步骤长度必须介于 0 和 50 之间")
	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}
	
	@Length(min=0, max=255, message="步骤名称长度必须介于 0 和 255 之间")
	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	
	@Length(min=0, max=64, message="处理人的角色长度必须介于 0 和 64 之间")
	public String getRollId() {
		return rollId;
	}

	public void setRollId(String rollId) {
		this.rollId = rollId;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
}