/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.process.entity;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 流程类型管理Entity
 * @author zhoudk
 * @version 2016-06-29
 */
public class SysProcessType extends DataEntity<SysProcessType> {
	
	private static final long serialVersionUID = 1L;
	private String processTypeCode;		// 流程类型编号
	private String processTypeName;		// 流程类型名称
	private String viewUrl;	// 是否生效
	
	public SysProcessType() {
		super();
	}

	public SysProcessType(String id){
		super(id);
	}

	@Length(min=0, max=100, message="流程类型编号长度必须介于 0 和 100 之间")
	public String getProcessTypeCode() {
		return processTypeCode;
	}

	public void setProcessTypeCode(String processTypeCode) {
		this.processTypeCode = processTypeCode;
	}
	
	@Length(min=0, max=200, message="流程类型名称长度必须介于 0 和 200 之间")
	public String getProcessTypeName() {
		return processTypeName;
	}

	public void setProcessTypeName(String processTypeName) {
		this.processTypeName = processTypeName;
	}

	@Length(min=0, max=500, message="流程数据查看url长度必须介于 0 和 200 之间")
	public String getViewUrl() {
		return viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}
	
}