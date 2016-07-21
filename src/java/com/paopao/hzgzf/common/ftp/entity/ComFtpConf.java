/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.ftp.entity;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * ftp服务器路径配置Entity
 * @author zdk
 * @version 2016-07-17
 */
public class ComFtpConf extends DataEntity<ComFtpConf> {
	
	private static final long serialVersionUID = 1L;
	private String ftpCode;		// ftp编码
	private String subClass;		// 子类型
	private String remotePath;		// 远程路径
	private String opId;		// op_id
	private String remark;		// remark
	
	public ComFtpConf() {
		super();
	}

	public ComFtpConf(String id){
		super(id);
	}

	@Length(min=0, max=50, message="ftp编码长度必须介于 0 和 50 之间")
	public String getFtpCode() {
		return ftpCode;
	}

	public void setFtpCode(String ftpCode) {
		this.ftpCode = ftpCode;
	}
	
	@Length(min=0, max=50, message="子类型长度必须介于 0 和 50 之间")
	public String getSubClass() {
		return subClass;
	}

	public void setSubClass(String subClass) {
		this.subClass = subClass;
	}
	
	@Length(min=0, max=500, message="远程路径长度必须介于 0 和 500 之间")
	public String getRemotePath() {
		return remotePath;
	}

	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
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