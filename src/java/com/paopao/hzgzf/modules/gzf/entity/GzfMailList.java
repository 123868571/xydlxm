/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 水表Entity
 * @author Hongjun
 * @version 2016-01-17
 */
public class GzfMailList extends DataEntity<GzfMailList> {
	
	private static final long serialVersionUID = 1L;
	private String gzfVillageId;		// 项目小区
	private String mailName;		// 设备名称
	private String manufactor;		// 设备厂家
	private String name;		// 负责人
	private String phone;		// 联系电话
	
	private GzfVillage gzfVillage;
	
	public GzfVillage getGzfVillage() {
		return gzfVillage;
	}

	public void setGzfVillage(GzfVillage gzfVillage) {
		this.gzfVillage = gzfVillage;
	}

	public GzfMailList() {
		super();
	}

	public GzfMailList(String id){
		super(id);
	}

	@Length(min=0, max=64, message="项目小区长度必须介于 0 和 64 之间")
	public String getGzfVillageId() {
		return gzfVillageId;
	}

	public void setGzfVillageId(String gzfVillageId) {
		this.gzfVillageId = gzfVillageId;
	}
	
	@Length(min=0, max=64, message="设备名称长度必须介于 0 和 64 之间")
	public String getMailName() {
		return mailName;
	}

	public void setMailName(String mailName) {
		this.mailName = mailName;
	}
	
	@Length(min=0, max=64, message="设备厂家长度必须介于 0 和 64 之间")
	public String getManufactor() {
		return manufactor;
	}

	public void setManufactor(String manufactor) {
		this.manufactor = manufactor;
	}
	
	@Length(min=0, max=64, message="负责人长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="联系电话长度必须介于 0 和 64 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}