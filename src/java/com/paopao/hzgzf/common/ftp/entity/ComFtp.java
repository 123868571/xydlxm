/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.ftp.entity;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * ftp服务器配置Entity
 * @author zdk
 * @version 2016-07-17
 */
public class ComFtp extends DataEntity<ComFtp> {
	
	private static final long serialVersionUID = 1L;
	private String ftpCode;		// ftp编码
	private String hostIp;		// 主机IP地址
	private String user;		// 用户
	private String password;		// 密码
	private String port;
	private String opId;		// op_id
	private String remark;		// remark
	
	public ComFtp() {
		super();
	}

	public ComFtp(String id){
		super(id);
	}

	@Length(min=0, max=50, message="ftp编码长度必须介于 0 和 50 之间")
	public String getFtpCode() {
		return ftpCode;
	}

	public void setFtpCode(String ftpCode) {
		this.ftpCode = ftpCode;
	}
	
	@Length(min=0, max=30, message="主机IP地址长度必须介于 0 和 30 之间")
	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	
	@Length(min=0, max=50, message="用户长度必须介于 0 和 50 之间")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	@Length(min=0, max=50, message="密码长度必须介于 0 和 50 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	@Length(min=0, max=10, message="port长度必须介于 0 和 10 之间")
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
}