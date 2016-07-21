/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.entity;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 局点管理表Entity
 * @author pjs
 * @version 2016-05-28
 */
public class ComJdManger extends DataEntity<ComJdManger> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String phone;		// 手机
	private String address;		// 地址
	private String point;		// 坐标
	private String photo;		// 照片
	
	public ComJdManger() {
		super();
	}

	public ComJdManger(String id){
		super(id);
	}

	@Length(min=1, max=100, message="名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="手机长度必须介于 0 和 100 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=1, max=200, message="地址长度必须介于 1 和 200 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=1, max=100, message="坐标长度必须介于 1 和 100 之间")
	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}
	
	@Length(min=0, max=1000, message="照片长度必须介于 0 和 1000 之间")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}