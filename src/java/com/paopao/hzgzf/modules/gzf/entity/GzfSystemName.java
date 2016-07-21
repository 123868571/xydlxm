/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 系统名称Entity
 * @author Hongjun
 * @version 2016-01-02
 */
public class GzfSystemName extends DataEntity<GzfSystemName> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String logo;		// 系统logo
	
	public GzfSystemName() {
		super();
	}

	public GzfSystemName(String id){
		super(id);
	}

	@Length(min=1, max=100, message="名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="系统logo长度必须介于 0 和 255 之间")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
}